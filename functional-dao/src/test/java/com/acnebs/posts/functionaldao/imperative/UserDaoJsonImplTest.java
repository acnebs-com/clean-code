package com.acnebs.posts.functionaldao.imperative;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserDaoJsonImplTest {
    @Test
    public void testLoadAllUsers_bad_path() throws Exception {
        UserDaoJsonImpl dao = new UserDaoJsonImpl("/i_do_no_exists.json");
        try {
            dao.loadAllUsers();
            fail("Should have thrown Exception");
        } catch (RuntimeException e) {
            assertEquals("loadAllUsers: IOException e", e.getMessage());
        }
    }

    @Test
    public void testLoadAllUsers_ok() throws Exception {
        UserDaoJsonImpl dao = new UserDaoJsonImpl("/users-integration-test.json");
        List<User> data = dao.loadAllUsers();
        assertEquals(3, data.size());
    }
}
