package com.library.pdfscraper.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

public class Pdfreader {

    public static String extractText(String pdfFilePath) {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            System.err.println("Erro ao ler o PDF: " + e.getMessage());
            return "";
        }
    }
}
