package edu.bsu.cs;

import javafx.application.Application;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // Console Mode
        if (args.length > 0 && args[0].equals("--console")) {
            ConsoleMenu consoleMenu = new ConsoleMenu();
            consoleMenu.showConsoleMenu();
        }
        // GUI Mode
        else {
            Application.launch(GUI.class, args);
        }
    }
}
