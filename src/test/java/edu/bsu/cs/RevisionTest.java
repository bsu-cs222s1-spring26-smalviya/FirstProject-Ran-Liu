package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RevisionTest {
    Revision revision = new Revision("Test", "2026-02-08T20:00:22Z");

    @Test
    public void getUser() {
        Assertions.assertEquals("Test", revision.getUser());
    }

    @Test
    public void getTimestamp() {
        Assertions.assertEquals("2026-02-08T20:00:22Z", revision.getTimestamp());
    }
}
