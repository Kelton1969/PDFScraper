package com.library.pdfscraper.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdfScraperApplication implements CommandLineRunner {

    @Autowired
    private PdfScraperService pdfScraperService;

    public static void main(String[] args) {
        SpringApplication.run(PdfScraperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Chama o serviço de scraping assim que a aplicação iniciar
        pdfScraperService.executeScraper();
    }
}
