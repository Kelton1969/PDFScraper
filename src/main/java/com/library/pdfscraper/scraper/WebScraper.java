package com.library.pdfscraper.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {
    public static List<String> fetchPDFLinks(String url) {
        List<String> pdfLinks = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href$=.pdf]");

            if (links.isEmpty()) {
                System.out.println("Nenhum PDF encontrado.");
                return pdfLinks;
            }

            for (Element link : links) {
                String linkText = link.text().toLowerCase();
                if (linkText.contains("anexo i") || linkText.contains("anexo ii")) {
                    pdfLinks.add(link.attr("abs:href"));
                    System.out.println("PDF encontrado: " + linkText);
                }
            }

            if (pdfLinks.isEmpty()) {
                System.out.println("Anexos I e II não encontrados.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao acessar a página: " + e.getMessage());
        }
        return pdfLinks;
    }
}
