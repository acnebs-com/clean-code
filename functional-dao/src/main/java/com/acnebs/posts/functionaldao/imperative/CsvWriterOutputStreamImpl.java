package com.acnebs.posts.functionaldao.imperative;
import java.io.OutputStream;

/**
 * Class CsvWriterFileImpl.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
class CsvWriterOutputStreamImpl implements CsvWriter {
    private final OutputStream outputStream;
    private final String charset = "UTF-8";

    public CsvWriterOutputStreamImpl(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void writeLine(final String line) {
        try {
            final byte[] bytes = (line + "\n").getBytes(charset);
            outputStream.write(bytes);
        } catch (Exception e) {
            throw new RuntimeException("writeLine: Exception e", e);
        }
    }
}
