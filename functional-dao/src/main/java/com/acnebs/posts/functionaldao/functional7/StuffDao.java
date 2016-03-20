package com.acnebs.posts.functionaldao.functional7;
/**
 * Interface StuffDao.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
interface StuffDao {
    void loadAllStuff(StuffCallback forEachStuff);
}
