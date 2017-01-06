package com.acnebs.posts.functionaldao.functional8;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

/**
 * Class UserExportServiceImplTest.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
public class UserExportServiceImplTest {
    List<String> lines;
    Consumer<String> forEachLine;

    @Before
    public void setUp() throws Exception {
        lines = new ArrayList<>();
        forEachLine = lines::add;
    }


    @Test
    public void test_no_data() throws Exception {
        createExporter().getCsvLines(forEachLine);
        assertEquals(0, lines.size());
    }

    @Test
    public void test_1_item() throws Exception {
        createExporter(
                new User("a1", "b1", "c1")
        ).getCsvLines(forEachLine);

        assertEquals(1, lines.size());
        assertEquals("a1;b1;c1", lines.get(0));
    }

    @Test
    public void test_2_items() throws Exception {
        createExporter(
                new User("a1", "b1", "c1"),
                new User("a2", "b2", "c2")
        ).getCsvLines(forEachLine);
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
