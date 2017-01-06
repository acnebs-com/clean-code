package com.acnebs.posts.functionaldao.imperative;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class User2CsvConverterTest {
    User2CsvConverter user2CsvConverter;

    @Before
    public void setUp() throws Exception {
        user2CsvConverter = new User2CsvConverter();
    }

    @Test
    public void testConvert_ok() throws Exception {
        final User user = new User("a", "b", "c");
        assertEquals("a;b;c", user2CsvConverter.convert(user));
    }

    @Test
    public void testConvert_values_null() throws Exception {
        final User user = new User(null, null, null);
        assertEquals(";;", user2CsvConverter.convert(user));
    }


}
