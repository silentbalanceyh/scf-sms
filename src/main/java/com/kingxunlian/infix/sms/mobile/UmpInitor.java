package com.kingxunlian.infix.sms.mobile;

import com.esms.PostMsg;
import com.esms.common.entity.Account;

import java.util.Properties;

class UmpInitor implements Key {
    private static final Properties PROP = Verifier.read();

    private static final PostMsg PM = new PostMsg();

    private static final Account ACCOUNT;

    static {
        /** 1.账号处理 **/
        final String name = PROP.getProperty(ACCOUNT_NAME);
        final String secret = PROP.getProperty(ACCOUNT_SECRET);
        /** 2.账号 **/
        ACCOUNT = new Account(name, secret);
        /** CM **/
        final String cmIp = PROP.getProperty(CM_HOST);
        final Integer cmPort = Integer.parseInt(PROP.getProperty(CM_PORT));
        PM.getCmHost().setHost(cmIp, cmPort);
        /** WS **/
        final String wsIp = PROP.getProperty(WS_HOST);
        final Integer wsPort = Integer.parseInt(PROP.getProperty(WS_PORT));
        PM.getWsHost().setHost(wsIp, wsPort);
    }

    private UmpInitor() {
    }

    static PostMsg getManager() {
        return PM;
    }

    static Account getAccount() {
        return ACCOUNT;
    }
}
