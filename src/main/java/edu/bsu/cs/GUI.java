package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class GUI extends Application {
    TextField titleTextField = new TextField();
    Button searchButton = new Button("Search");
    TextArea revisionTextArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        configureSearchButton();
        revisionTextArea.setMinHeight(500);
        primaryStage.setTitle("Wikipedia Revision Reporter");
        Pane pane = new VBox();
        pane.getChildren().addAll(
                new Label("Title:"),
                titleTextField,
                searchButton,
                new Label("Revision"),
                revisionTextArea
        );
        primaryStage.setScene(new Scene(pane));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void configureSearchButton() {
        searchButton.setOnAction(event -> {
            try {
                startSearch();
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void startSearch() throws IOException, URISyntaxException {
        WikipediaService wikipediaService = new WikipediaService();
        try {
            wikipediaService.searchTitle(titleTextField.getText());
        } catch (NetworkException e) {
            System.err.println("[Error] Network connection failed, please check your network status!");
        } catch (InvalidInputException e) {
            System.err.println("[Warning] Invalid input!");
        } catch (PageMissingException e) {
            System.err.println("[Error] No corresponding Wikipedia page found!");
        }
        revisionTextArea.setText(wikipediaService.getRevisionOutput());
    }
}
