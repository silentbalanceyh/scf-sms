package com.kingxunlian.infix.sms.mobile;

import com.fasterxml.jackson.core.Json;
import com.fasterxml.jackson.core.JsonObject;
import com.kingxunlian.infix.sms.Tpl;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.Map;

/**
 *
 */
public class OrgInvitorTpl implements Tpl {


    String data;

    public OrgInvitorTpl() {
    }

    public OrgInvitorTpl(final String content) {
        this.data = content;
    }

    @Override
    public String batch() {
        return "Test Pack";
    }

    @Override
    public String to(final JsonObject params) {
        final Map<String, String> args = Json.toMap(params);
        final StrSubstitutor stitutor = new StrSubstitutor(args);
        return stitutor.replace(this.data);
    }
}
