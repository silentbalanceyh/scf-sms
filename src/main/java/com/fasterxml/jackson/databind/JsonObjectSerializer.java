package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * @author Lang
 */
public class JsonObjectSerializer extends JsonSerializer<JsonObject> {    // NOPMD

    /** **/
    @Override
    public void serialize(final JsonObject jsonObject, final JsonGenerator jsonGen, final SerializerProvider provider)
            throws IOException, JsonProcessingException {
        final String value = jsonObject.encode();
        jsonGen.writeString(value);
    }
}
