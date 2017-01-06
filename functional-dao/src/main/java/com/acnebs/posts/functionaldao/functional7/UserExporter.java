package com.acnebs.posts.functionaldao.functional7;
/**
 * Class UserExportServiceImpl.
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

    public int getCsvLines(final LineConsumer forEachLine) {
        final int out[] = new int[]{0};

        userDao.loadAllUsers(
                new UserConsumer() {
                    @Override
                    public void doOnUser(final User user) {
                        final String line = user2CsvConverter.convert(user);
                        forEachLine.doOnLine(line);
                        out[0] = out[0] + 1;
                    }
                }
        );

        return out[0];
    }
}
