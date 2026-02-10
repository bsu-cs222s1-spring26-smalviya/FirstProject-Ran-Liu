package edu.bsu.cs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikiConnect {
    URLConnection connection;

    public void connectToWiki(String title) throws IOException, URISyntaxException {
        String encodedUrlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(title, Charset.defaultCharset()) +
                "&rvprop=timestamp" + URLEncoder.encode("|", Charset.defaultCharset()) + "user&rvlimit=4&redirects";
        URI uri = new URI(encodedUrlString);
        connection = uri.toURL().openConnection();
        connection.setRequestProperty("User-Agent", "Wikipedia Revision Reporter/0.1 (qingyang.ran@bsu.edu)");
        connection.connect();
    }

    public String getJsonAsString() throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }
}
