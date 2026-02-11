package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;

public class WikipediaService {
    WikipediaConnection wikipediaConnection = new WikipediaConnection();
    private String jsonData;
    RevisionFormatter revisionFormatter;

    public void searchTitle(String title) throws IOException, URISyntaxException {
        wikipediaConnection.connectWikipedia(title);
        jsonData = wikipediaConnection.getJsonAsString();
    }

    public String getRevisionTable() {
        revisionFormatter = new RevisionFormatter(jsonData);
        return revisionFormatter.getRevisionTable();
    }
}
