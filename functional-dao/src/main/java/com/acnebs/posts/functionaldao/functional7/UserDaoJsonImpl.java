package com.acnebs.posts.functionaldao.functional7;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Class UserDaoJsonImpl.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
class UserDaoJsonImpl implements UserDao {

    private final String path;

    public UserDaoJsonImpl(final String path) {
        this.path = path;
    }

    @Override
    public void loadAllUsers(final UserConsumer userConsumer) {
        final List<User> userList = loadAllUsers();
        for (User user : userList) {
            userConsumer.doOnUser(user);
        }
    }


    List<User> loadAllUsers() {
        try {
            final URL resource = this.getClass().getResource(path);
            if (resource == null) {
                throw new FileNotFoundException(path);
            } else {
                final InputStream is = resource.openStream();
                final ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(is, new TypeReference<List<User>>() {
                });
                // in Java 7 we cannot use streams and spliterator...
            }
        } catch (IOException e) {
            throw new RuntimeException("loadAllUsers: IOException e", e);
        }
    }
}
