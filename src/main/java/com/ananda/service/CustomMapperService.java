package com.ananda.service;

import org.springframework.stereotype.Service;

import java.io.IOException;


@Service("CustomMapperService")
public class CustomMapperService implements JsonMapperService{


    @Override
    public String toJson(Object instance) throws IOException {
        return null;
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return null;
    }
}
