package com.acnebs.posts.functionaldao.imperative;
import java.util.List;

/**
 * Class UserDaoDummyImpl.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
class UserDaoDummyImpl implements UserDao {

    private List<User> database;

    public UserDaoDummyImpl(final List<User> database) {
        this.database = database;
    }

    @Override
    public List<User> loadAllUsers() {
        return database;
    }
}
