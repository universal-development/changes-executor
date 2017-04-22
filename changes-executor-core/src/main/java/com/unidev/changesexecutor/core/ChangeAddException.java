package com.unidev.changesexecutor.core;

public class ChangeAddException extends RuntimeException {
    public ChangeAddException() {
        super();
    }

    public ChangeAddException(String message) {
        super(message);
    }

    public ChangeAddException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeAddException(Throwable cause) {
        super(cause);
    }
}
