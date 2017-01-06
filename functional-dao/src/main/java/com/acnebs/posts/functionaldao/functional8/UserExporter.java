package com.acnebs.posts.functionaldao.functional8;
import java.util.function.Consumer;

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

    public void getCsvLines(final Consumer<String> lineConsumer) {
        userDao.loadAllUsers(
                (user) -> {
                    final String line = user2CsvConverter.convert(user);
                    lineConsumer.accept(line);
                }
        );
    }
}
