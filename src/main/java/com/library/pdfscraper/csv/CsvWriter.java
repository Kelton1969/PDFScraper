package com.library.pdfscraper.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    public static void saveDataToCsv(List<String[]> data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String[] row : data) {
                writer.write(String.join(",", row)); 
                writer.newLine(); 
            }
            System.out.println("Dados salvos em " + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao salvar CSV: " + e.getMessage());
        }
    }
}
