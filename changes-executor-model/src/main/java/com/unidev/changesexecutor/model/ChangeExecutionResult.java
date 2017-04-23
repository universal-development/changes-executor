package com.unidev.changesexecutor.model;

/**
 * Result of change execution
 */
public class ChangeExecutionResult<T extends ChangeExecutionResult.Result> {

    public enum Result {SUCCESS, ERROR}

    private Change change;
    private T result;
    private String message;

    public ChangeExecutionResult() {
    }

    public ChangeExecutionResult(Change change, T result, String message) {
        this.change = change;
        this.result = result;
        this.message = message;
    }

    public boolean isSuccess() {
        return result == Result.SUCCESS;
    }

    public Change getChange() {
        return change;
    }

    public void setChange(Change change) {
        this.change = change;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChangeExecutionResult{");
        sb.append("change=").append(change);
        sb.append(", result=").append(result);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
