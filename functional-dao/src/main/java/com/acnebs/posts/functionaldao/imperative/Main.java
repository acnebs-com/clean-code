package com.acnebs.posts.functionaldao.imperative;
import java.util.List;

public class Main {

    private final StuffExporter stuffExporter;
    private final CsvWriter csvWriter;

    public Main(final StuffDao stuffDao, final CsvWriter csvWriter) {
        this.stuffExporter = new StuffExporter(stuffDao);
        this.csvWriter = csvWriter;
    }

    int process() {
        final List<String> csvLines = stuffExporter.getCsvLines();

        int linesWritten = 0;
        csvWriter.writeLine("Key;Value");
        for (String line : csvLines) {
            csvWriter.writeLine(line);
            linesWritten++;
        }

        return linesWritten;
    }

    public static void main(final String[] args) {
        final StuffDaoJsonImpl stuffDao = new StuffDaoJsonImpl("/stuff.json");
        CsvWriter csvWriter = new CsvWriterOutputStreamImpl(System.out);
        final Main main = new Main(stuffDao, csvWriter);
        main.process();
    }
}
