package com.example.java_all.core.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserJsonComponent {

    public static class UserSerializer extends JsonSerializer<UserSerialize> {
        @Override
        public void serialize(UserSerialize user, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("user_name", user.getUser());
            gen.writeNumberField("user_age", user.getAge());
            gen.writeEndObject();
        }
    }

    public static class UserDeserializer extends JsonDeserializer<UserSerialize> {
        @Override
        public UserSerialize deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            ObjectNode node = p.getCodec().readTree(p);
            String name = node.get("user_name").asText();
            int age = node.get("user_age").asInt();
            return new UserSerialize(name, age);
        }
    }


}
