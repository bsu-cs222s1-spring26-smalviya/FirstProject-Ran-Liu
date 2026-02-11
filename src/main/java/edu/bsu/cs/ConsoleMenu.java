package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ConsoleMenu {
    WikipediaService wikipediaService = new WikipediaService();
    Scanner scanner = new Scanner(System.in);

    public void showConsoleMenu() throws IOException, URISyntaxException {
        System.out.println("Wikipedia Revision Reporter");
        System.out.print("Search a title to begin: ");
        String title = scanner.nextLine();
        wikipediaService.searchTitle(title);
        System.out.print(wikipediaService.getRevisionTable());
    }
}
