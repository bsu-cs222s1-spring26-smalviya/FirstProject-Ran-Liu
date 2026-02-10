package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Menu {
    Wiki wiki = new Wiki();
    Scanner scanner = new Scanner(System.in);

    public void showMenu() throws IOException, URISyntaxException {
        System.out.println("Wikipedia Revision Reporter");
        System.out.print("Search a title to begin: ");
        String title = scanner.nextLine();
        wiki.searchTitle(title);
        System.out.print(wiki.getRevisionTable());
    }
}
