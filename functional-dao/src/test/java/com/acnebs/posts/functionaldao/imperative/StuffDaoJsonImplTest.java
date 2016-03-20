package com.acnebs.posts.functionaldao.imperative;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StuffDaoJsonImplTest {
    @Test
    public void testLoadAllStuff_bad_path() throws Exception {
        StuffDaoJsonImpl dao = new StuffDaoJsonImpl("/i_do_no_exists.json");
        try {
            dao.loadAllStuff();
            fail("Should have thrown Exception");
        } catch (RuntimeException e) {
            assertEquals("loadAllStuff: IOException e", e.getMessage());
        }
    }

    @Test
    public void testLoadAllStuff_ok() throws Exception {
        StuffDaoJsonImpl dao = new StuffDaoJsonImpl("/stuff.json");
        List<Stuff> data = dao.loadAllStuff();
        assertEquals(3, data.size());
    }
}
