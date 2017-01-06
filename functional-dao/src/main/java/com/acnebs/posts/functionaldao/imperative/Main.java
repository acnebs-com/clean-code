package com.acnebs.posts.functionaldao.imperative;
import java.util.List;

public class Main {

    private final UserExporter userExporter;
    private final CsvWriter csvWriter;

    public Main(final UserDao userDao, final CsvWriter csvWriter) {
        this.userExporter = new UserExporter(userDao);
        this.csvWriter = csvWriter;
    }

    int process() {
        final List<String> csvLines = userExporter.getCsvLines();

        int linesWritten = 0;
        csvWriter.writeLine("First Name;Last Name;Email");
        for (String line : csvLines) {
            csvWriter.writeLine(line);
            linesWritten++;
        }

        return linesWritten;
    }

    public static void main(final String[] args) {
        final UserDaoJsonImpl userDao = new UserDaoJsonImpl("/users.json");
        CsvWriter csvWriter = new CsvWriterOutputStreamImpl(System.out);
        final Main main = new Main(userDao, csvWriter);
        main.process();
    }
}
