package com.ananda.exception;

public final class LineException extends RuntimeException {

    public LineException() {
        super();
    }

    public LineException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LineException(final String message) {
        super(message);
    }

    public LineException(final Throwable cause) {
        super(cause);
    }

}
