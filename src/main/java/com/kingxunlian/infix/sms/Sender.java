package com.kingxunlian.infix.sms;

import com.fasterxml.jackson.core.JsonObject;

import java.util.Set;

/**
 *
 */
public interface Sender {
    /**
     * 给一个人发一条
     *
     * @param to
     * @param params
     * @return
     */
    boolean send(String to, JsonObject params);

    /**
     * 给多个人发同一条
     *
     * @param to
     * @param params
     * @return
     */
    boolean send(Set<String> to, JsonObject params);
}
