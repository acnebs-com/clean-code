package com.acnebs.posts.functionaldao.functional8;
public class Main {

    private final StuffExporter stuffExporter;
    private final CsvWriter csvWriter;

    public Main(final StuffDao stuffDao, final CsvWriter csvWriter) {
        this.stuffExporter = new StuffExporter(stuffDao);
        this.csvWriter = csvWriter;
    }

    void process() {
        csvWriter.writeLine("Key;Value");
        stuffExporter.getCsvLines(csvWriter::writeLine);
    }

    public static void main(final String[] args) {
        final StuffDaoJsonImpl stuffDao = new StuffDaoJsonImpl("/stuff.json");
        CsvWriter csvWriter = new CsvWriterOutputStreamImpl(System.out);
        final Main main = new Main(stuffDao, csvWriter);
        main.process();
    }
}
