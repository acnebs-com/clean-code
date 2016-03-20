package com.acnebs.posts.functionaldao.imperative;
import java.util.List;

/**
 * Class CsvWriterDummyImpl.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
class CsvWriterDummyImpl implements CsvWriter {
    private final List<String> lines;

    public CsvWriterDummyImpl(final List<String> lines) {
        this.lines = lines;
    }

    @Override
    public void writeLine(final String line) {
        lines.add(line + "\n");
    }
}
