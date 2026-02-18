package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ConsoleMenu {
    WikipediaService wikipediaService = new WikipediaService();
    Scanner scanner = new Scanner(System.in);

    public void showConsoleMenu() {
        System.out.println("---------- Wikipedia Revision Reporter ----------");
        System.out.println("      Developed by Qingyang Ran, Yixiao Liu      ");
        System.out.println("            Version 0.1.0 (2026-02-11)           ");
        System.out.println();
        System.out.print("Search a title to begin: ");
        String title = scanner.nextLine();
        try {
            wikipediaService.searchTitle(title);
        } catch (NetworkException e) {
            System.err.println("[Error] Network connection failed, please check your network status!");
            System.exit(1);
        } catch (InvalidInputException e) {
            System.err.println("[Warning] Invalid input!");
            System.exit(1);
        } catch (PageMissingException e) {
            System.err.println("[Information] No corresponding Wikipedia page found!");
            System.exit(1);
        }
        System.out.print(wikipediaService.getRevisionOutput());
    }
}
