package com.fasterxml.jackson.core;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;
import com.kingxunlian.exception.internal.DecodeException;
import com.kingxunlian.exception.internal.EncodeException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Json {

    public static ObjectMapper mapper = new ObjectMapper();
    public static ObjectMapper prettyMapper = new ObjectMapper();

    static {

        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false);

        prettyMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        prettyMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        final SimpleModule module = new SimpleModule();

        module.addSerializer(JsonObject.class, new JsonObjectSerializer());
        module.addSerializer(JsonArray.class, new JsonArraySerializer());

        module.addSerializer(Instant.class, new InstantSerializer());
        module.addSerializer(byte[].class, new ByteArraySerializer());

        mapper.registerModule(module);
        prettyMapper.registerModule(module);
    }

    public static <T> Map<String, T> toMap(final JsonObject data) {
        final Map<String, T> map = new HashMap<>();
        if (null != data) {
            for (final String field : data.fieldNames()) {
                final Object value = data.getValue(field);
                if (null != value) {
                    // TODO：默认传入对的数据，未做校验
                    map.put(field, (T) value);
                }
            }
        }
        return map;
    }

    public static String encode(final Object obj) throws EncodeException {
        try {
            return mapper.writeValueAsString(obj);
        } catch (final Exception e) {
            throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
        }
    }

    public static String encodePrettily(final Object obj) throws EncodeException {
        try {
            return prettyMapper.writeValueAsString(obj);
        } catch (final Exception e) {
            throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
        }
    }

    public static <T> T decodeValue(final String str, final Class<T> clazz) throws DecodeException {
        try {
            return mapper.readValue(str, clazz);
        } catch (final Exception e) {
            throw new DecodeException("Failed to decode:" + e.getMessage(), e);
        }
    }

    static Integer toInteger(final Number number) {
        if (number == null) {
            return null;
        } else if (number instanceof Integer) {
            return (Integer) number;
        } else {
            return number.intValue();
        }
    }

    static Long toLong(final Number number) {
        if (number == null) {
            return null;
        } else if (number instanceof Long) {
            return (Long) number; // Avoids unnecessary unbox/box
        } else {
            return number.longValue();
        }
    }

    static Double toDouble(final Number number) {
        if (number == null) {
            return null;
        } else if (number instanceof Double) {
            return (Double) number; // Avoids unnecessary unbox/box
        } else {
            return number.doubleValue();
        }
    }

    static Float toFloat(final Number number) {
        if (number == null) {
            return null;
        } else if (number instanceof Float) {
            return (Float) number; // Avoids unnecessary unbox/box
        } else {
            return number.floatValue();
        }
    }

    static Object checkAndCopy(Object val, final boolean copy) {
        if (val == null) {
            // OK
        } else if (val instanceof Number && !(val instanceof BigDecimal)) {
            // OK
        } else if (val instanceof Boolean) {
            // OK
        } else if (val instanceof String) {
            // OK
        } else if (val instanceof Character) {
            // OK
        } else if (val instanceof CharSequence) {
            val = val.toString();
        } else if (val instanceof JsonObject) {
            if (copy) {
                val = ((JsonObject) val).copy();
            }
        } else if (val instanceof JsonArray) {
            if (copy) {
                val = ((JsonArray) val).copy();
            }
        } else if (val instanceof Map) {
            if (copy) {
                val = (new JsonObject((Map) val)).copy();
            } else {
                val = new JsonObject((Map) val);
            }
        } else if (val instanceof List) {
            if (copy) {
                val = (new JsonArray((List) val)).copy();
            } else {
                val = new JsonArray((List) val);
            }
        } else if (val instanceof byte[]) {
            val = Base64.getEncoder().encodeToString((byte[]) val);
        } else if (val instanceof Instant) {
            val = ISO_INSTANT.format((Instant) val);
        } else {
            throw new IllegalStateException("Illegal type in JsonObject: " + val.getClass());
        }
        return val;
    }

    static <T> Stream<T> asStream(final Iterator<T> sourceIterator) {
        final Iterable<T> iterable = () -> sourceIterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static boolean isJObject(final String literal) {
        boolean isCorrect;
        try {
            new JsonObject(literal);
            isCorrect = true;
        } catch (final DecodeException ex) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isJArray(final String literal) {
        boolean isCorrect;
        try {
            new JsonArray(literal);
            isCorrect = true;
        } catch (final DecodeException ex) {
            isCorrect = false;
        }
        return isCorrect;
    }

    private static class JsonObjectSerializer extends JsonSerializer<JsonObject> {
        @Override
        public void serialize(final JsonObject value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
            jgen.writeObject(value.getMap());
        }
    }

    private static class JsonArraySerializer extends JsonSerializer<JsonArray> {
        @Override
        public void serialize(final JsonArray value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
            jgen.writeObject(value.getList());
        }
    }

    private static class InstantSerializer extends JsonSerializer<Instant> {
        @Override
        public void serialize(final Instant value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
            jgen.writeString(ISO_INSTANT.format(value));
        }
    }
}
