package com.acnebs.posts.functionaldao.functional7;
public class Main {

    private final StuffExporter stuffExporter;
    private final CsvWriter csvWriter;

    public Main(final StuffDao stuffDao, final CsvWriter csvWriter) {
        this.stuffExporter = new StuffExporter(stuffDao);
        this.csvWriter = csvWriter;
    }

    int process() {
        csvWriter.writeLine("Key;Value");
        return stuffExporter.getCsvLines(
                new LineCallback() {
                    @Override
                    public void doOnLine(final String line) {
                        csvWriter.writeLine(line);
                    }
                }
        );
    }

    public static void main(final String[] args) {
        final StuffDaoJsonImpl stuffDao = new StuffDaoJsonImpl("/stuff.json");
        CsvWriter csvWriter = new CsvWriterOutputStreamImpl(System.out);
        final Main main = new Main(stuffDao, csvWriter);
        main.process();
    }
}
