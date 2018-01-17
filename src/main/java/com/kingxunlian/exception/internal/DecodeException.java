package com.kingxunlian.exception.internal;

import com.kingxunlian.exception.ScfRunException;

public class DecodeException extends ScfRunException {

    public DecodeException() {
    }

    public DecodeException(final String message) {
        super(message);
    }

    public DecodeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
