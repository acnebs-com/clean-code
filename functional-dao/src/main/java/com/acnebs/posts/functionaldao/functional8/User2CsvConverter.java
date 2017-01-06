package com.acnebs.posts.functionaldao.functional8;
/**
 * Class User2CsvConverter.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
class User2CsvConverter {
    private static final String FORMAT = "%s;%s;%s";

    String convert(final User user) {
        return String.format(
                FORMAT,
                getCleansedString(user.getFirstName()),
                getCleansedString(user.getLastName()),
                getCleansedString(user.getEmail())
        );
    }

    private String getCleansedString(final String value) {
        return value == null ? "" : value;
    }
}
