package com.acnebs.posts.functionaldao.functional7;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StuffDaoJsonImplTest {
    @Test
    public void testLoadAllStuff_bad_path() throws Exception {
        StuffDaoJsonImpl dao = new StuffDaoJsonImpl("/i_do_no_exists.json");
        try {
            dao.loadAllStuff(stuff -> {});
            fail("Should have thrown Exception");
        } catch (RuntimeException e) {
            assertEquals("loadAllStuff: IOException e", e.getMessage());
        }
    }

    @Test
    public void testLoadAllStuff_ok() throws Exception {
        StuffDaoJsonImpl dao = new StuffDaoJsonImpl("/stuff.json");
        List<Stuff> data = new ArrayList<>();
        dao.loadAllStuff(data::add);
        assertEquals(3, data.size());
    }
}
