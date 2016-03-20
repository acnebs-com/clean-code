package com.acnebs.posts.functionaldao.functional7;
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

    public int getCsvLines(final LineCallback forEachLine) {
        final int out[] = new int[]{0};

        stuffDao.loadAllStuff(
                new StuffCallback() {
                    @Override
                    public void doOnStuff(final Stuff stuff) {
                        final String line = stuff2CsvConverter.convert(stuff);
                        forEachLine.doOnLine(line);
                        out[0] = out[0] + 1;
                    }
                }
        );

        return out[0];
    }
}
