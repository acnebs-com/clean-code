package com.acnebs.posts.functionaldao.imperative;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> getCsvLines() {
        final List<Stuff> stuffList = stuffDao.loadAllStuff();
        final List<String> lines = new ArrayList<>();
        for (Stuff stuffItem : stuffList) {
            lines.add(stuff2CsvConverter.convert(stuffItem));
        }

        return lines;
    }
}
