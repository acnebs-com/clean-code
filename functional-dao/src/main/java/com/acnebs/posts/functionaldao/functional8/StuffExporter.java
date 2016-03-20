package com.acnebs.posts.functionaldao.functional8;
import java.util.function.Consumer;

/**
 * Class StuffExportServiceImpl.
 * <p>
 * Created by andreas.czakaj on 04.03.2016
 *
 * @author andreas.czakaj
 */
class StuffExporter {
    private final Stuff2CsvConverter stuff2CsvConverter = new Stuff2CsvConverter();

    private final StuffDao stuffDao;

    public StuffExporter(final StuffDao stuffDao) {
        this.stuffDao = stuffDao;
    }

    public void getCsvLines(final Consumer<String> forEachLine) {
        stuffDao.loadAllStuff(
                (stuff) -> {
                    final String line = stuff2CsvConverter.convert(stuff);
                    forEachLine.accept(line);
                }
        );
    }
}
