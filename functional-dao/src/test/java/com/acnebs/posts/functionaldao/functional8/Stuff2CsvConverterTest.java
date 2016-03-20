package com.acnebs.posts.functionaldao.functional8;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Stuff2CsvConverterTest {
    Stuff2CsvConverter stuff2CsvConverter;

    @Before
    public void setUp() throws Exception {
        stuff2CsvConverter = new Stuff2CsvConverter();
    }

    @Test
    public void testConvert_ok() throws Exception {
        final Stuff stuff = new Stuff("a", "b");
        assertEquals("a;b", stuff2CsvConverter.convert(stuff));
    }

    @Test
    public void testConvert_values_null() throws Exception {
        final Stuff stuff = new Stuff(null, null);
        assertEquals(";", stuff2CsvConverter.convert(stuff));
    }


}
