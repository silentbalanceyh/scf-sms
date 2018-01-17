package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;

import java.io.IOException;

/**
 * @author Lang
 */

public class JsonObjectDeserializer extends JsonDeserializer<JsonObject> { // NOPMD
    /** **/
    @Override
    public JsonObject deserialize(final JsonParser parser, final DeserializationContext context)
            throws IOException, JsonProcessingException {
        final ObjectCodec codec = parser.getCodec();
        final JsonNode node = codec.readTree(parser);
        return new JsonObject(node.toString());
    }
}
