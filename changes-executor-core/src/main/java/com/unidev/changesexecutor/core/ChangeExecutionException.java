package com.unidev.changesexecutor.core;

/**
 * Exception thrown if change execution fails
 */
public class ChangeExecutionException extends RuntimeException {

    public ChangeExecutionException() {
        super();
    }

    public ChangeExecutionException(String message) {
        super(message);
    }

    public ChangeExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeExecutionException(Throwable cause) {
        super(cause);
    }
}
