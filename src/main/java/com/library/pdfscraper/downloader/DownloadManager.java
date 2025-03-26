package com.library.pdfscraper.downloader;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;

public class DownloadManager {

    public static void downloadPDF(String pdfUrl, String fileName) {
        try {
            URL url = new URL(pdfUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

          
            if (connection.getResponseCode() != 200) {
                System.err.println("Falha ao conectar: " + connection.getResponseMessage());
                return;
            }

           
            File file = new File(fileName);
            file.getParentFile().mkdirs();

   
            try (InputStream in = connection.getInputStream();
                 FileOutputStream out = new FileOutputStream(file)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                    
                }
                System.out.println("Download concluído: " + fileName);
            }

        } catch (IOException e) {
            System.err.println("Erro ao baixar PDF da URL: " + pdfUrl);
            System.err.println("Detalhes do erro: " + e.getMessage());
        }
    }
}
