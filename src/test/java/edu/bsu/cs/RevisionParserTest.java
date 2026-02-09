package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class RevisionParserTest {
    RevisionParser revisionParser;

    public RevisionParserTest() throws NullPointerException, IOException {
        try (InputStream jsonFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("test.json")) {
            if (jsonFile == null) {
                throw new IllegalStateException("[Error] JSON file not found! ");
            }
            String jsonData = new String(Objects.requireNonNull(jsonFile).readAllBytes(), Charset.defaultCharset());
            revisionParser = new RevisionParser(jsonData);
        }
    }

    @Test
    public void revisionsCount() {
        Assertions.assertEquals(4, revisionParser.getRevisionsCount());
    }

    @Test
    public void revisionsUser() {
        Assertions.assertEquals("Smasongarrison", revisionParser.getRevisionsUser(0));
        Assertions.assertEquals("Smasongarrison", revisionParser.getRevisionsUser(1));
        Assertions.assertEquals("Citation bot", revisionParser.getRevisionsUser(2));
        Assertions.assertEquals("Smasongarrison", revisionParser.getRevisionsUser(3));
    }

    @Test
    public void revisionTime() {
        Assertions.assertEquals("2026-01-25T23:51:24Z", revisionParser.getRevisionsTime(0));
        Assertions.assertEquals("2026-01-25T23:26:54Z", revisionParser.getRevisionsTime(1));
        Assertions.assertEquals("2026-01-21T20:29:46Z", revisionParser.getRevisionsTime(2));
        Assertions.assertEquals("2026-01-20T05:29:08Z", revisionParser.getRevisionsTime(3));
    }

    @Test
    public void isRedirect() {
        Assertions.assertTrue(revisionParser.isRevisionsRedirects());
    }

    @Test
    public void revisionRedirectFrom() {
        Assertions.assertEquals("John von Neuman", revisionParser.getRevisionsRedirectFrom());
    }

    @Test
    public void revisionRedirectTo() {
        Assertions.assertEquals("John von Neumann", revisionParser.getRevisionsRedirectTo());
    }
}
