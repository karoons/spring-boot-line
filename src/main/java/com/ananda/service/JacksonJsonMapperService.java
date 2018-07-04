package com.ananda.service;

//import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

@Service("JacksonJsonMapperService")
public class JacksonJsonMapperService implements JsonMapperService{

//    @Autowired
//    private ObjectMapper mapper;

    private final ObjectMapper mapper;

    JacksonJsonMapperService(final ObjectMapper mapper) {
        super();
        this.mapper = checkNotNull(mapper);
    }

    @Override
    public String toJson(final Object instance) throws IOException {
        return mapper.writeValueAsString(instance);
    }

    @Override
    public <T> T fromJson(final String json, final Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

}
