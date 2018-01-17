package com.kingxunlian.exception.internal;

import com.kingxunlian.exception.ScfRunException;

import java.text.MessageFormat;

public class KeyConflictException
        extends ScfRunException {

    public KeyConflictException(final String file, final String key,
                                final Class<?> expected, final Class<?> actual) {
        super(MessageFormat.format(Info.KEY_CONFLICT,
                file, key, expected.getName(), actual.getName()));
    }
}
