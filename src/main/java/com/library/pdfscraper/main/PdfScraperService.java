package com.library.pdfscraper.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.pdfscraper.downloader.DownloadManager;
import com.library.pdfscraper.scraper.WebScraper;
import com.library.pdfscraper.zip.ZipManager;
import com.library.pdfscraper.pdf.Pdfreader;
import com.library.pdfscraper.csv.CsvWriter;

@Service
public class PdfScraperService {

    public void executeScraper(String downloadDirectory) {
    
        File directory = new File(downloadDirectory);
        if (!directory.exists()) {
            directory.mkdirs(); 
            System.out.println("Diretório criado: " + downloadDirectory);
        }

        String baseUrl = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        List<String> pdfLinks = WebScraper.fetchPDFLinks(baseUrl);

        if (pdfLinks.isEmpty()) return;

        List<String> files = new ArrayList<>();
        
        for (int i = 0; i < pdfLinks.size(); i++) {
            String fileName = downloadDirectory + "/anexo_" + (i + 1) + ".pdf"; 
            DownloadManager.downloadPDF(pdfLinks.get(i), fileName);
            files.add(fileName);
        }

        String extractedText = Pdfreader.extractText(files.get(0)); // Leitura do PDF do Anexo I
        
        List<String[]> processedData = processPdfText(extractedText);

        CsvWriter.saveDataToCsv(processedData, downloadDirectory + "/dados_extraidos.csv");

        ZipManager.zipFiles(files, downloadDirectory + "/anexos.zip");
        
        System.out.println("Processo concluído!");
    }

    private List<String[]> processPdfText(String extractedText) {
        
        extractedText = extractedText.replace("OD", "Descrição Completa de OD");
        extractedText = extractedText.replace("AMB", "Descrição Completa de AMB");

        List<String[]> csvData = new ArrayList<>();
        
        csvData.add(new String[]{"Coluna1", "Coluna2", "Coluna3"});

        String[] lines = extractedText.split("\n");
        for (String line : lines) {
            String[] columns = line.split("\\s+"); 
            csvData.add(columns);
        }

        return csvData;
    }
}
