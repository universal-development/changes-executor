package com.unidev.changesexecutor.model;

/**
 * Result of change execution
 */
public class ChangeExecutionResult<T extends Change> {

    public enum Result {SUCCESS, ERROR}

    private T change;
    private Result result;
    private String message;

    public ChangeExecutionResult() {
    }

    public ChangeExecutionResult(T change, Result result, String message) {
        this.change = change;
        this.result = result;
        this.message = message;
    }

    public boolean isSuccess() {
        return result == Result.SUCCESS;
    }

    public T getChange() {
        return change;
    }

    public void setChange(T change) {
        this.change = change;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
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
