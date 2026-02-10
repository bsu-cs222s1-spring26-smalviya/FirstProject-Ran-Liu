package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;

public class Wiki {
    WikiConnect wikiConnect = new WikiConnect();
    private String jsonData;
    RevisionFormatter revisionFormatter;

    public void searchTitle(String title) throws IOException, URISyntaxException {
        wikiConnect.connectToWiki(title);
        jsonData = wikiConnect.getJsonAsString();
    }

    public String getRevisionTable() {
        revisionFormatter = new RevisionFormatter(jsonData);
        return revisionFormatter.getRevisionTable();
    }
}
