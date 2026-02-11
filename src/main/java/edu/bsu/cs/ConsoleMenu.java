package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ConsoleMenu {
    WikipediaService wikipediaService = new WikipediaService();
    Scanner scanner = new Scanner(System.in);

    public void showConsoleMenu() throws IOException, URISyntaxException {
        System.out.println("---------- Wikipedia Revision Reporter ----------");
        System.out.println("      Developed by Qingyang Ran, Yixiao Liu      ");
        System.out.println("            Version 0.1.0 (2026-02-11)           ");
        System.out.println();
        System.out.print("Search a title to begin: ");
        String title = scanner.nextLine();
        if(title.isBlank()){
            System.err.println("[Warning] Invalid input!");
            System.exit(1);
        }
        wikipediaService.searchTitle(title);
        System.out.print(wikipediaService.getRevisionTable());
    }
}
