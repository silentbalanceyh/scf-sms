package com.kingxunlian.exception;

public abstract class ScfRunException extends RuntimeException {

    private String message;

    protected ScfRunException() {
    }

    protected ScfRunException(final String message) {
        this(message, null);
    }

    public ScfRunException(final String message, final Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
