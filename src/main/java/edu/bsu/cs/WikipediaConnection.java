package edu.bsu.cs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikipediaConnection {
    URLConnection connection;

    public void connectWikipedia(String title) throws IOException, URISyntaxException {
        try {
            String encodedUrlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                    URLEncoder.encode(title, Charset.defaultCharset()) +
                    "&rvprop=timestamp" + URLEncoder.encode("|", Charset.defaultCharset()) + "user&rvlimit=15&redirects";
            URI uri = new URI(encodedUrlString);
            connection = uri.toURL().openConnection();
            connection.setRequestProperty("User-Agent", "Wikipedia Revision Reporter/0.1 (qingyang.ran@bsu.edu)");
            connection.connect();
        } catch (IOException e) {
            System.err.println("[Error] Network connection failed, please check your network status!");
        }
    }

    public String getJsonAsString() throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }
}
