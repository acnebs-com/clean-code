package com.acnebs.posts.functionaldao.functional8;
import java.util.function.Consumer;

/**
 * Interface UserDao.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
interface UserDao {
    void loadAllUsers(Consumer<User> userConsumer);
}
