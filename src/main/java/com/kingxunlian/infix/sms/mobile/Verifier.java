package com.kingxunlian.infix.sms.mobile;

import com.kingxunlian.exception.internal.KeyConflictException;
import com.kingxunlian.exception.internal.KeyMissingException;
import com.kingxunlian.platform.tool.IO;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * 参数验证器
 */
class Verifier implements Path, Key {

    /**
     * 验证读取
     *
     * @return
     */
    static Properties read() {
        final Properties prop = IO.getProp(FILE_CONFIG);
        // 1.验证必须配置
        verifyRequired(prop,
                ACCOUNT_NAME,
                ACCOUNT_SECRET,
                CM_HOST,
                CM_PORT,
                WS_HOST,
                WS_PORT);
        // 2.验证特殊配置类型
        verifyNumber(prop,
                CM_PORT,
                WS_PORT);
        return prop;
    }

    private static void verifyNumber(final Properties prop, final String... typedKeys) {
        for (final String key : typedKeys) {
            final String value = prop.getProperty(key);
            final boolean numeric = StringUtils.isNumeric(value);
            if (!numeric) {
                throw new KeyConflictException(FILE_CONFIG, key, Number.class, String.class);
            }
        }

    }

    private static void verifyRequired(final Properties prop, final String... requiredKeys) {
        for (final String existed : requiredKeys) {
            if (!prop.containsKey(existed)) {
                throw new KeyMissingException(FILE_CONFIG, existed);
            }
        }
    }
}
