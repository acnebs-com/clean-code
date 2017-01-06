package com.acnebs.posts.functionaldao.imperative;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class UserExportServiceImplTest.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
public class UserExportServiceImplTest {

    @Test
    public void test_no_data() throws Exception {
        final List<String> lines = createExporter().getCsvLines();
        assertEquals(0, lines.size());
    }

    @Test
    public void test_1_item() throws Exception {
        final List<String> lines = createExporter(
                new User("a", "b", "c")
        ).getCsvLines();
        assertEquals(1, lines.size());
        assertEquals("a;b;c", lines.get(0));
    }

    @Test
    public void test_2_items() throws Exception {
        final List<String> lines = createExporter(
                new User("a1", "b1", "c1"),
                new User("a2", "b2", "c2")
        ).getCsvLines();
        assertEquals(2, lines.size());
        assertEquals("a1;b1;c1", lines.get(0));
        assertEquals("a2;b2;c2", lines.get(1));
    }

    UserExporter createExporter(final User...data) {
        List<User> database = new ArrayList<>();
        Collections.addAll(database, data);
        return new UserExporter(new UserDaoDummyImpl(database));
    }
}
