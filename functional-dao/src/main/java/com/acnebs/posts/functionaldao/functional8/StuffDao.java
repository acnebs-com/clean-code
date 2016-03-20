package com.acnebs.posts.functionaldao.functional8;
import java.util.function.Consumer;

/**
 * Interface StuffDao.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
interface StuffDao {
    void loadAllStuff(Consumer<Stuff> forEachStuff);
}
