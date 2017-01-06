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
 * Class UserDaoJsonImpl.
 * <p>
 * Created by andreas.czakaj on 05.03.2016
 *
 * @author andreas.czakaj
 */
class UserDaoJsonImpl implements UserDao {

    private final String path;

    public UserDaoJsonImpl(final String path) {
        this.path = path;
    }

    @Override
    public void loadAllUsers(final Consumer<User> userConsumer) {
        loadAllUsers(
                userConsumer,
                e -> {
                    throw new RuntimeException("loadAllUsers: Exception e: " + e.getMessage(), e);
                }
        );
    }

    public void loadAllUsers(final Consumer<User> userConsumer,
                             final Consumer<Exception> onError) {
        try {
            final URL resource = this.getClass().getResource(path);
            if (resource == null) {
                throw new FileNotFoundException(path);
            } else {
                final InputStream is = resource.openStream();
                final ObjectMapper mapper = new ObjectMapper();
                final TypeReference<User> valueTypeRef = new TypeReference<User>() {};
                final JsonNode jn = mapper.readValue(is, JsonNode.class);
                final Spliterator<JsonNode> jsonNodeSpliterator = jn.spliterator();

                StreamSupport.stream(jsonNodeSpliterator, true)
                        .forEach(jsonNode -> {
                            try {
                                final JsonParser jsonParser = jsonNode.traverse(mapper);
                                final User user = jsonParser.readValueAs(valueTypeRef);
                                userConsumer.accept(user);
                            } catch (IOException e) {
                                onError.accept(e);
                            }
                        });
            }
        } catch (IOException e) {
            onError.accept(e);
        }
    }
}
