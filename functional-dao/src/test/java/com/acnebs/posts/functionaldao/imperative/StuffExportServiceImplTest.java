package com.acnebs.posts.functionaldao.imperative;
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

    @Test
    public void test_no_data() throws Exception {
        final List<String> lines = createExporter().getCsvLines();
        assertEquals(0, lines.size());
    }

    @Test
    public void test_1_item() throws Exception {
        final List<String> lines = createExporter(
                new Stuff("key1", "val1")
        ).getCsvLines();
        assertEquals(1, lines.size());
        assertEquals("key1;val1", lines.get(0));
    }

    @Test
    public void test_2_items() throws Exception {
        final List<String> lines = createExporter(
                new Stuff("key1", "val1"),
                new Stuff("key2", "val2")
        ).getCsvLines();
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
