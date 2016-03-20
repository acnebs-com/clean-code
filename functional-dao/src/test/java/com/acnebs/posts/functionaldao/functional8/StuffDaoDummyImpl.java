package com.acnebs.posts.functionaldao.functional8;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class StuffDaoDummyImpl.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
class StuffDaoDummyImpl implements StuffDao {

    private List<Stuff> database;

    public StuffDaoDummyImpl(final List<Stuff> database) {
        this.database = database;
    }

    @Override
    public void loadAllStuff(final Consumer<Stuff> forEachStuff) {
        for (Stuff stuff : database) {
            forEachStuff.accept(stuff);
        }
    }
}
