package com.acnebs.posts.functionaldao.imperative;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Class StuffDaoJsonImpl.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
class StuffDaoJsonImpl implements StuffDao {

    private final String path;

    public StuffDaoJsonImpl(final String path) {
        this.path = path;
    }

    @Override
    public List<Stuff> loadAllStuff() {
        try {
            final URL resource = this.getClass().getResource(path);
            if (resource == null) {
                throw new FileNotFoundException(path);
            } else {
                final InputStream is = resource.openStream();
                final ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(is, new TypeReference<List<Stuff>>() {
                });
            }
        } catch (IOException e) {
            throw new RuntimeException("loadAllStuff: IOException e", e);
        }
    }
}
