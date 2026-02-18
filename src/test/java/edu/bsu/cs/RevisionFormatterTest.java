package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class RevisionFormatterTest {
    RevisionFormatter revisionFormatter;

    public RevisionFormatterTest() throws NullPointerException, IOException {
        try (InputStream jsonFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("Case_Redirect.json")) {
            if (jsonFile == null) {
                throw new IllegalStateException("[Error] JSON file not found! ");
            }
            String jsonData = new String(Objects.requireNonNull(jsonFile).readAllBytes(), Charset.defaultCharset());
            revisionFormatter = new RevisionFormatter(jsonData);
        }
    }

    @Test
    public void getRevisionOutput() {
        String expectedStr = "Redirected to John von Neumann\n" +
                "1  2026-01-25T23:51:24Z  Smasongarrison\n" +
                "2  2026-01-25T23:26:54Z  Smasongarrison\n" +
                "3  2026-01-21T20:29:46Z  Citation bot\n" +
                "4  2026-01-20T05:29:08Z  Smasongarrison\n";
        Assertions.assertEquals(expectedStr, revisionFormatter.getRevisionOutput());
    }
}
