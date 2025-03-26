package com.library.pdfscraper.main;

import com.library.pdfscraper.scraper.WebScraper;
import com.library.pdfscraper.downloader.DownloadManager;
import com.library.pdfscraper.zip.ZipManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PdfScraperService {

    public void executeScraper() {
        String baseUrl = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        List<String> pdfLinks = WebScraper.fetchPDFLinks(baseUrl);

        if (pdfLinks.isEmpty()) return;

        List<String> files = new ArrayList<>();
        for (int i = 0; i < pdfLinks.size(); i++) {
            String fileName = "anexo_" + (i + 1) + ".pdf";
            DownloadManager.downloadPDF(pdfLinks.get(i), fileName);
            files.add(fileName);
        }

        ZipManager.zipFiles(files, "anexos.zip");
        System.out.println("Processo concluído!");
    }
}
