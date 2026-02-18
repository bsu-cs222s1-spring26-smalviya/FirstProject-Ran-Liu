package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {
    private List<Revision> revisions = new ArrayList<>();
    private boolean redirectStatus = false;
    private String redirectFrom;
    private String redirectTo;
    private boolean missingStatus = true;

    public RevisionParser(String jsonData) {
        List<Map<String, String>> rawMissing = JsonPath.read(jsonData, "$..pages..missing");
        if (rawMissing.isEmpty()) {
            missingStatus = false;
        }
        List<Map<String, String>> rawRevisions = JsonPath.read(jsonData, "$..revisions[*]");
        for (Map<String, String> i : rawRevisions) {
            String user = i.get("user");
            String timestamp = i.get("timestamp");
            revisions.add(new Revision(user, timestamp));
        }
        List<Map<String, String>> rawRedirects = JsonPath.read(jsonData, "$..redirects[0]");
        if (!rawRedirects.isEmpty()) {
            redirectStatus = true;
            redirectFrom = rawRedirects.getFirst().get("from");
            redirectTo = rawRedirects.getFirst().get("to");
        }
    }

    public int getRevisionsCount() {
        return revisions.size();
    }

    public String getRevisionsUser(int index) {
        return revisions.get(index).getUser();
    }

    public String getRevisionsTimestamp(int index) {
        return revisions.get(index).getTimestamp();
    }

    public boolean isRevisionsRedirects() {
        return redirectStatus;
    }

    public String getRevisionsRedirectFrom() {
        return redirectFrom;
    }

    public String getRevisionsRedirectTo() {
        return redirectTo;
    }

    public boolean isRevisionsMissing() {
        return missingStatus;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }
}
