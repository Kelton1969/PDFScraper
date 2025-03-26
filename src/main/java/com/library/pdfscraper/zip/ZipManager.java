package com.library.pdfscraper.zip;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipManager {

    public static void zipFiles(List<String> files, String outputZip) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputZip))) {
            for (String file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                   
                    zos.putNextEntry(new ZipEntry(new File(file).getName()));
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) >= 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                    System.out.println("Arquivo adicionado ao ZIP: " + file);
                } catch (IOException e) {
                    System.err.println("Erro ao adicionar o arquivo " + file + " ao ZIP: " + e.getMessage());
                }
            }
            System.out.println("Arquivos compactados em: " + outputZip);
        } 
    }
}
