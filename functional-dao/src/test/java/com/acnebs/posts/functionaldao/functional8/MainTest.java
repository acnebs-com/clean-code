package com.acnebs.posts.functionaldao.functional8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void testMain() throws Exception {
        Main.main(new String[]{});
        assertTrue("It should run without exceptions", true);
    }

    @Test
    public void testProcess() throws Exception {
        final List<User> data = new ArrayList<User>() {{
            add(new User("Joey", "Ramone", "joey@ramones.net"));
            add(new User("CJ", "Ramone", "cj@ramones.net"));
        }};
        final UserDaoDummyImpl userDao = new UserDaoDummyImpl(data);

        final List<String> fakeFile = new ArrayList<>();
        final CsvWriter csvWriter = new CsvWriterDummyImpl(fakeFile);

        Main main = new Main(userDao, csvWriter);
        main.process();
        assertEquals("Should have written 3 lines", 3, fakeFile.size());
    }
}
