package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class RevisionParserTest {
    RevisionParser revisionParser_Redirect;
    RevisionParser revisionParser_NoRedirect;

    public RevisionParserTest() throws NullPointerException, IOException {
        try (InputStream jsonFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("Case_Redirect.json")) {
            if (jsonFile == null) {
                throw new IllegalStateException("[Error] JSON file not found! ");
            }
            String jsonData = new String(Objects.requireNonNull(jsonFile).readAllBytes(), Charset.defaultCharset());
            revisionParser_Redirect = new RevisionParser(jsonData);
        }
        try (InputStream jsonFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("Case_NoRedirect.json")) {
            if (jsonFile == null) {
                throw new IllegalStateException("[Error] JSON file not found! ");
            }
            String jsonData = new String(Objects.requireNonNull(jsonFile).readAllBytes(), Charset.defaultCharset());
            revisionParser_NoRedirect = new RevisionParser(jsonData);
        }
    }

    @Test
    public void getRevisionsCount() {
        Assertions.assertEquals(4, revisionParser_Redirect.getRevisionsCount());
        Assertions.assertEquals(4, revisionParser_NoRedirect.getRevisionsCount());
    }

    @Test
    public void getRevisionsUser() {
        Assertions.assertEquals("Smasongarrison", revisionParser_Redirect.getRevisionsUser(0));
        Assertions.assertEquals("Smasongarrison", revisionParser_Redirect.getRevisionsUser(1));
        Assertions.assertEquals("Citation bot", revisionParser_Redirect.getRevisionsUser(2));
        Assertions.assertEquals("Smasongarrison", revisionParser_Redirect.getRevisionsUser(3));
        Assertions.assertEquals("Smasongarrison", revisionParser_NoRedirect.getRevisionsUser(0));
        Assertions.assertEquals("Smasongarrison", revisionParser_NoRedirect.getRevisionsUser(1));
        Assertions.assertEquals("Citation bot", revisionParser_NoRedirect.getRevisionsUser(2));
        Assertions.assertEquals("Smasongarrison", revisionParser_NoRedirect.getRevisionsUser(3));
    }

    @Test
    public void getRevisionTimestamp() {
        Assertions.assertEquals("2026-01-25T23:51:24Z", revisionParser_Redirect.getRevisionsTimestamp(0));
        Assertions.assertEquals("2026-01-25T23:26:54Z", revisionParser_Redirect.getRevisionsTimestamp(1));
        Assertions.assertEquals("2026-01-21T20:29:46Z", revisionParser_Redirect.getRevisionsTimestamp(2));
        Assertions.assertEquals("2026-01-20T05:29:08Z", revisionParser_Redirect.getRevisionsTimestamp(3));
        Assertions.assertEquals("2026-01-25T23:51:24Z", revisionParser_NoRedirect.getRevisionsTimestamp(0));
        Assertions.assertEquals("2026-01-25T23:26:54Z", revisionParser_NoRedirect.getRevisionsTimestamp(1));
        Assertions.assertEquals("2026-01-21T20:29:46Z", revisionParser_NoRedirect.getRevisionsTimestamp(2));
        Assertions.assertEquals("2026-01-20T05:29:08Z", revisionParser_NoRedirect.getRevisionsTimestamp(3));
    }

    @Test
    public void isRevisionsRedirects() {
        Assertions.assertTrue(revisionParser_Redirect.isRevisionsRedirects());
        Assertions.assertFalse(revisionParser_NoRedirect.isRevisionsRedirects());
    }

    @Test
    public void getRevisionsRedirectFrom() {
        Assertions.assertEquals("John von Neuman", revisionParser_Redirect.getRevisionsRedirectFrom());
    }

    @Test
    public void getRevisionsRedirectTo() {
        Assertions.assertEquals("John von Neumann", revisionParser_Redirect.getRevisionsRedirectTo());
    }
}
