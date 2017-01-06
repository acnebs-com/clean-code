package com.acnebs.posts.functionaldao.functional7;
public class Main {

    private final UserExporter userExporter;
    private final CsvWriter csvWriter;

    public Main(final UserDao userDao, final CsvWriter csvWriter) {
        this.userExporter = new UserExporter(userDao);
        this.csvWriter = csvWriter;
    }

    int process() {
        csvWriter.writeLine("First Name;Last Name;Email");
        return userExporter.getCsvLines(
                new LineConsumer() {
                    @Override
                    public void doOnLine(final String line) {
                        csvWriter.writeLine(line);
                    }
                }
        );
    }

    public static void main(final String[] args) {
        final UserDaoJsonImpl userDao = new UserDaoJsonImpl("/users.json");
        CsvWriter csvWriter = new CsvWriterOutputStreamImpl(System.out);
        final Main main = new Main(userDao, csvWriter);
        main.process();
    }
}
