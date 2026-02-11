package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;

public class WikipediaService {
    private String jsonData;

    public void searchTitle(String title) throws IOException, URISyntaxException {
        WikipediaConnection wikipediaConnection = new WikipediaConnection();
        wikipediaConnection.connectWikipedia(title);
        jsonData = wikipediaConnection.getJsonAsString();
    }

    public String getRevisionTable() {
        RevisionFormatter revisionFormatter = new RevisionFormatter(jsonData);
        return revisionFormatter.getRevisionTable();
    }
}
