package com.acnebs.posts.functionaldao.imperative;
import java.util.ArrayList;
import java.util.List;

/**
 * Class UserExporter.
 * <p>
 * Created by andreas.czakaj on 04.03.2016
 *
 * @author andreas.czakaj
 */
class UserExporter {
    private final User2CsvConverter user2CsvConverter = new User2CsvConverter();

    private final UserDao userDao;

    public UserExporter(final UserDao userDao) {
        this.userDao = userDao;
    }

    public List<String> getCsvLines() {
        final List<User> userList = userDao.loadAllUsers();
        final List<String> lines = new ArrayList<>();
        for (User userItem : userList) {
            lines.add(user2CsvConverter.convert(userItem));
        }

        return lines;
    }
}
