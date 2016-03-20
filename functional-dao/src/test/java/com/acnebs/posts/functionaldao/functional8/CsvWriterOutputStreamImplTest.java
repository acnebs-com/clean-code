package com.acnebs.posts.functionaldao.functional8;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CsvWriterOutputStreamImplTest {
    @Test
    public void testWriteLine_ok() throws Exception {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CsvWriterOutputStreamImpl csvWriter = new CsvWriterOutputStreamImpl(outputStream);
        csvWriter.writeLine("abc");

        final String output = outputStream.toString("UTF-8");
        assertEquals("abc\n", output);
    }

    @Test
    public void testWriteLine_error() throws Exception {
        CsvWriterOutputStreamImpl csvWriter = new CsvWriterOutputStreamImpl(null);
        try {
            csvWriter.writeLine("abc");
            fail("Should have thrown an Exception");
        } catch (RuntimeException e) {
            assertEquals("writeLine: Exception e", e.getMessage());
        }
    }
}
