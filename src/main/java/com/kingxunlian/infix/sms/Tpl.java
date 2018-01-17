package com.kingxunlian.infix.sms;

import com.fasterxml.jackson.core.JsonObject;

public interface Tpl {

    /**
     * 返回模板名称
     *
     * @return
     */
    String batch();

    /**
     * 根据参数生成最终发出去的内容信息
     *
     * @param params
     * @return
     */
    String to(JsonObject params);
}
