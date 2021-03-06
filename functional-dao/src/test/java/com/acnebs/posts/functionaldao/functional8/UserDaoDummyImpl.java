package com.acnebs.posts.functionaldao.functional8;
import java.util.List;
import java.util.function.Consumer;

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
    public void loadAllUsers(final Consumer<User> userConsumer) {
        for (User user : database) {
            userConsumer.accept(user);
        }
    }
}
