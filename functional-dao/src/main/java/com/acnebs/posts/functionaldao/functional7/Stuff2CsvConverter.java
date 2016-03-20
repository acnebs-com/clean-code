package com.acnebs.posts.functionaldao.functional7;
/**
 * Class Stuff2CsvConverter.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
class Stuff2CsvConverter {
    private static final String FORMAT = "%s;%s";

    String convert(final Stuff stuff) {
        return String.format(
                FORMAT,
                getCleansedString(stuff.getKey()),
                getCleansedString(stuff.getValue())
        );
    }

    private String getCleansedString(final String value) {
        return value == null ? "" : value;
    }
}
