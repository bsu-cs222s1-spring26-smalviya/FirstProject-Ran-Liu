package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WikipediaService {
    private String jsonData;
    RevisionFormatter revisionFormatter;

    public void searchTitle(String title) throws NetworkException, InvalidInputException, PageMissingException {
        if (title.isBlank()) {
            throw new InvalidInputException();
        }
        WikipediaConnection wikipediaConnection = new WikipediaConnection();
        try {
            wikipediaConnection.connectWikipedia(title);
            jsonData = wikipediaConnection.getJsonString();
            revisionFormatter = new RevisionFormatter(jsonData);
            if (revisionFormatter.revisionParser.isRevisionsMissing()) {
                throw new PageMissingException();
            }
        } catch (IOException | URISyntaxException e) {
            throw new NetworkException();
        }
    }

    public String getRevisionOutput() {
        return revisionFormatter.getRevisionOutput();
    }

    public List<Revision> getRevisionList() {
        return revisionFormatter.revisionParser.getRevisions();
    }
}
