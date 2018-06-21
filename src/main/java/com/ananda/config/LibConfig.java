package com.ananda.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class LibConfig {

    @Bean(name = "jackSon")
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
//        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }
}
