package com.acnebs.posts.functionaldao.functional8;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

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
    public void loadAllStuff(final Consumer<Stuff> forEachStuff) {
        loadAllStuff(
                forEachStuff,
                e -> {throw new RuntimeException("loadAllStuff: Exception e: " + e.getMessage(), e);}
        );
    }

    public void loadAllStuff(final Consumer<Stuff> forEachStuff, final Consumer<Exception> onError) {
        try {
            final URL resource = this.getClass().getResource(path);
            if (resource == null) {
                throw new FileNotFoundException(path);
            } else {
                final InputStream is = resource.openStream();
                final ObjectMapper mapper = new ObjectMapper();
                final TypeReference<Stuff> valueTypeRef = new TypeReference<Stuff>() {};
                final JsonNode jn = mapper.readValue(is, JsonNode.class);
                final Spliterator<JsonNode> jsonNodeSpliterator = jn.spliterator();

                StreamSupport.stream(jsonNodeSpliterator, true)
                        .forEach(jsonNode -> {
                            try {
                                final JsonParser jsonParser = jsonNode.traverse(mapper);
                                final Stuff stuff = jsonParser.readValueAs(valueTypeRef);
                                forEachStuff.accept(stuff);
                            } catch (IOException e) {
                                onError.accept(e);
                            }
                        });
            }
        } catch (IOException e) {
            throw new RuntimeException("loadAllStuff: IOException e", e);
        }
    }
}
