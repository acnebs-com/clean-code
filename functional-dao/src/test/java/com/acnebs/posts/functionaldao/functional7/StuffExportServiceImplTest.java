package com.acnebs.posts.functionaldao.functional7;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class StuffExportServiceImplTest.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
public class StuffExportServiceImplTest {
    List<String> lines;
    LineCallback forEachLine;

    @Before
    public void setUp() throws Exception {
        lines = new ArrayList<>();
        forEachLine = new LineCallback() {
            @Override
            public void doOnLine(final String line) {
                lines.add(line);
            }
        };
    }


    @Test
    public void test_no_data() throws Exception {
        int result = createExporter().getCsvLines(forEachLine);
        assertEquals(0, result);
        assertEquals(0, lines.size());
    }

    @Test
    public void test_1_item() throws Exception {
        int result = createExporter(
                new Stuff("key1", "val1")
        ).getCsvLines(forEachLine);

        assertEquals(1, result);
        assertEquals(1, lines.size());
        assertEquals("key1;val1", lines.get(0));
    }

    @Test
    public void test_2_items() throws Exception {
        int result = createExporter(
                new Stuff("key1", "val1"),
                new Stuff("key2", "val2")
        ).getCsvLines(forEachLine);
        assertEquals(2, result);
        assertEquals(2, lines.size());
        assertEquals("key1;val1", lines.get(0));
        assertEquals("key2;val2", lines.get(1));
    }

    StuffExporter createExporter(final Stuff...data) {
        List<Stuff> database = new ArrayList<>();
        Collections.addAll(database, data);
        return new StuffExporter(new StuffDaoDummyImpl(database));
    }
}
