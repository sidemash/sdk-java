package com.sidemash.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Utils {

    public static class Js {
        public static ObjectMapper Mapper = configureMapper(new ObjectMapper());

        public static ObjectMapper configureMapper(ObjectMapper mapper){
            return mapper.registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        }

        public static <T> T fromJson(String s) throws JsonProcessingException {
            return Mapper.readValue(s, new TypeReference<T>() {});
        }

        public static <T> JsonNode toJsonNode(T obj) {
            return Mapper.valueToTree(obj);
        }

        public static <T> String toJson(T obj) {
            try {
                return Mapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
