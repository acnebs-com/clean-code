package com.acnebs.posts.functionaldao.imperative;
import java.util.List;

/**
 * Interface StuffDao.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
interface StuffDao {
    List<Stuff> loadAllStuff();
}
