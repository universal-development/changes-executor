package com.unidev.changesexecutor.model;

/**
 * Result of change execution
 */
public class ChangeExecutionResult {

    public enum Result {SUCCESS, ERROR}

    private long changeId;
    private Result result;
    private String message;

    public long getChangeId() {
        return changeId;
    }

    public void setChangeId(long changeId) {
        this.changeId = changeId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeExecutionResult that = (ChangeExecutionResult) o;

        if (changeId != that.changeId) return false;
        return result == that.result;
    }

    @Override
    public int hashCode() {
        int result1 = (int) (changeId ^ (changeId >>> 32));
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChangeExecutionResult{");
        sb.append("changeId=").append(changeId);
        sb.append(", result=").append(result);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
