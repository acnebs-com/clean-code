package com.acnebs.posts.functionaldao.functional7;
/**
 * Class Stuff.
 * <p>
 * Created by andreas.czakaj on 04.03.2016
 *
 * @author andreas.czakaj
 */
class Stuff {
    private String key;
    private String value;

    public Stuff(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    public Stuff() {
        // needed for jackson
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
