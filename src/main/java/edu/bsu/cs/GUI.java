package edu.bsu.cs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
    TextField titleTextField = new TextField();
    Button searchButton = new Button("Search");
    TableView<Revision> revisionTable = new TableView<>();
    Label revisionTitle = new Label();
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        configureSearchButton();
        configureRevisionTitle();
        revisionTable.setMinSize(500, 300);
        primaryStage.setTitle("Wikipedia Revision Reporter");
        primaryStage.setScene(new Scene(configurePane()));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private Pane configurePane() {
        Pane pane = new VBox();
        pane.getChildren().addAll(
                new Label("Title"),
                titleTextField,
                searchButton,
                revisionTitle,
                new Label("Revision"),
                revisionTable,
                new Label(" Version 0.2.0 (2026-02-18)\t" +
                        "Developed by Qingyang Ran, Yixiao Liu")
        );
        return pane;
    }

    private void configureSearchButton() {
        searchButton.setOnAction(event -> {
            startSearch();
        });
    }

    private void configureRevisionTitle() {
        revisionTitle.setText("N/A");
        revisionTitle.setFont(new Font(20));
    }

    private void startSearch() {
        setDisable(true);
        WikipediaService wikipediaService = new WikipediaService();
        try {
            wikipediaService.searchTitle(titleTextField.getText());
            if (wikipediaService.revisionFormatter.revisionParser.isRevisionsRedirects()) {
                revisionTitle.setText(String.format("%s (Redirect from %s)",
                        wikipediaService.revisionFormatter.revisionParser.getRevisionsRedirectTo(),
                        wikipediaService.revisionFormatter.revisionParser.getRevisionsRedirectFrom()
                ));
            } else {
                revisionTitle.setText(wikipediaService.revisionFormatter.revisionParser.getTitle());
            }
        } catch (NetworkException e) {
            System.err.println("[Error] Network connection failed, please check your network status!");
            revisionTitle.setText("N/A");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Network connection failed! \nPlease check your network status.");
            alert.initOwner(primaryStage);
            alert.showAndWait();
            setDisable(false);
        } catch (InvalidInputException e) {
            System.err.println("[Warning] Invalid input!");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Input! \nPlease enter in the correct format.");
            alert.initOwner(primaryStage);
            alert.showAndWait();
            setDisable(false);
        } catch (PageMissingException e) {
            System.err.println("[Information] No corresponding Wikipedia page found!");
            revisionTitle.setText("Page not found");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No corresponding Wikipedia page found! \nMake sure you enter the correct title.");
            alert.initOwner(primaryStage);
            alert.showAndWait();
            setDisable(false);
        }

        ObservableList<Revision> revisionList = FXCollections.observableArrayList(wikipediaService.getRevisionList());
        revisionTable.setItems(revisionList);
        TableColumn<Revision, Void> indexColumn = new TableColumn<>("#");
        TableColumn<Revision, String> userColumn = new TableColumn<>("User");
        TableColumn<Revision, String> timestampColumn = new TableColumn<>("Timestamp");
        indexColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        revisionTable.getColumns().setAll(indexColumn, userColumn, timestampColumn);
        setDisable(false);
    }

    private void setDisable(boolean bool) {
        if (bool) {
            searchButton.setDisable(true);
            titleTextField.setDisable(true);
            revisionTable.setDisable(true);
        } else {
            searchButton.setDisable(false);
            titleTextField.setDisable(false);
            revisionTable.setDisable(false);
        }
    }
}
