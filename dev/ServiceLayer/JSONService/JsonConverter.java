package ServiceLayer.JsonService;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JsonConverter {
    public static String toJson(Object object) throws JsonProcessingException {
        ObjectMapper ow = new ObjectMapper();
        ow.enable(SerializationFeature.INDENT_OUTPUT);
        return ow.writeValueAsString(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    public static <T> T fromJson(String json, TypeReference<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    //region LocalDate, LocalTime and LocalDateTime serializer and deserializer
    public static class LocalDateDeserializer extends StdDeserializer<LocalDate> {

        @Serial
        private static final long serialVersionUID = 1L;

        protected LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override
        public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            return LocalDate.parse(jp.readValueAs(String.class));
        }

    }

    public static class LocalDateSerializer extends StdSerializer<LocalDate> {

        @Serial
        private static final long serialVersionUID = 1L;

        public LocalDateSerializer() {
            super(LocalDate.class);
        }

        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider sp) throws IOException, JsonProcessingException {
            gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    public static class LocalTimeDeserializer extends StdDeserializer<LocalTime> {

        @Serial
        private static final long serialVersionUID = 1L;

        protected LocalTimeDeserializer() {
            super(LocalTime.class);
        }

        @Override
        public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            return LocalTime.parse(jp.readValueAs(String.class));
        }

    }

    public static class LocalTimeSerializer extends StdSerializer<LocalTime> {

        @Serial
        private static final long serialVersionUID = 1L;

        public LocalTimeSerializer() {
            super(LocalTime.class);
        }

        @Override
        public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider sp) throws IOException, JsonProcessingException {
            gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_TIME));
        }
    }

    public static class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

        @Serial
        private static final long serialVersionUID = 1L;

        protected LocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }

        @Override
        public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            return LocalDateTime.parse(jp.readValueAs(String.class));
        }

    }

    public static class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

        @Serial
        private static final long serialVersionUID = 1L;

        public LocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider sp) throws IOException, JsonProcessingException {
            gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
    }

    //endregion
}