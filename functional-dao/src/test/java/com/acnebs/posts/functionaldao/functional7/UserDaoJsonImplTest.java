package com.acnebs.posts.functionaldao.functional7;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserDaoJsonImplTest {
    @Test
    public void testLoadAllUsers_bad_path() throws Exception {
        UserDaoJsonImpl dao = new UserDaoJsonImpl("/i_do_no_exist.json");
        try {
            dao.loadAllUsers(user -> {});
            fail("Should have thrown Exception");
        } catch (RuntimeException e) {
            assertEquals("loadAllUsers: IOException e", e.getMessage());
        }
    }

    @Test
    public void testLoadAllUsers_ok() throws Exception {
        UserDaoJsonImpl dao = new UserDaoJsonImpl("/users-integration-test.json");
        List<User> data = new ArrayList<>();
        dao.loadAllUsers(data::add);
        assertEquals(3, data.size());
    }
}
