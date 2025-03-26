package com.library.pdfscraper.downloader;
//Criando uma dowload com uma lógica: O código começa criando um InputStream a partir da URL fornecida (pdfUrl). O InputStream é utilizado para ler os dados do arquivo PDF diretamente da web.
import java.io.*;
import java.net.URL;

public class DownloadManager {

    public static void downloadPDF(String pdfUrl, String fileName) {
    	
        try (InputStream in = new URL(pdfUrl).openStream(); FileOutputStream out = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Download feito: " + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao baixar PDF: " + e.getMessage());
        }
    }
}
