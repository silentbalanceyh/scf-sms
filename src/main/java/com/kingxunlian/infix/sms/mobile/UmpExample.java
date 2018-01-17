package com.kingxunlian.infix.sms.mobile;

import com.fasterxml.jackson.core.Json;
import com.fasterxml.jackson.core.JsonObject;
import com.kingxunlian.infix.sms.Tpl;
import com.kingxunlian.platform.tool.IO;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.Map;

/**
 *
 */
public class UmpExample implements Tpl {

    private transient final JsonObject data;

    public UmpExample() {
        this("sms/example.json");
    }

    public UmpExample(final String file) {
        this.data = IO.getJObject(file);
    }

    @Override
    public String batch() {
        return "Test Pack";
    }

    @Override
    public String to(final JsonObject params) {
        final String pattern = this.data.getString("pattern");
        final Map<String, String> args = Json.toMap(params);
        final StrSubstitutor stitutor = new StrSubstitutor(args);
        return stitutor.replace(pattern);
    }
}
