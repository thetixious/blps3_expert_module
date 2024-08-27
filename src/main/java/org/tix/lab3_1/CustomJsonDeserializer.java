package org.tix.lab3_1;

import org.apache.kafka.common.errors.SerializationException;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomJsonDeserializer<T> extends JsonDeserializer<T> {

    @Override
    public T deserialize(String topic, byte[] data) throws SerializationException {
        String jsonData = new String(data, StandardCharsets.UTF_8);

        // Меняем название пакета
        jsonData = jsonData.replace("com.blps.lab3.model.mainDB", "org.tix.lab3_1.model");

        try {
            return objectMapper.readValue(jsonData, targetType);
        } catch (IOException e) {
            throw new SerializationException("Error deserializing JSON message", e);
        }
    }
}