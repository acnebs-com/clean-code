package com.acnebs.posts.functionaldao.functional7;
/**
 * Interface UserDao.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
interface UserDao {
    void loadAllUsers(UserConsumer userConsumer);
}
