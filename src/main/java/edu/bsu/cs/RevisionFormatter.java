package edu.bsu.cs;

public class RevisionFormatter {
    RevisionParser revisionParser;

    public RevisionFormatter(String jsonData) {
        revisionParser = new RevisionParser(jsonData);
    }

    public String getRevisionOutput() {
        StringBuilder revisionTable = new StringBuilder();
        if (revisionParser.isRevisionsRedirects()) {
            revisionTable.append("Redirected to ").append(revisionParser.getRevisionsRedirectTo()).append("\n");
        }
        for (int i = 0; i < revisionParser.getRevisionsCount(); i++) {
            revisionTable.append(i + 1).append("  ")
                    .append(revisionParser.getRevisionsTimestamp(i)).append("  ")
                    .append(revisionParser.getRevisionsUser(i)).append("\n");
        }
        return revisionTable.toString();
    }
}
