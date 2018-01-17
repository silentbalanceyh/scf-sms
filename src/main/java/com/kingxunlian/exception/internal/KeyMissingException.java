package com.kingxunlian.exception.internal;

import com.kingxunlian.exception.ScfRunException;

import java.text.MessageFormat;

public class KeyMissingException
        extends ScfRunException {

    public KeyMissingException(final String file, final String... key) {
        super(MessageFormat.format(Info.KEY_MISSING, file, key));
    }
}
