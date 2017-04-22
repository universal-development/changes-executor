package com.unidev.changesexecutor.model;

/**
 * Abstract change representation
 */
public abstract class AbstractChange implements Change {

    private final long changeOrder;
    private final String changeName;

    public AbstractChange(long changeOrder, String changeName) {
        this.changeOrder = changeOrder;
        this.changeName = changeName;
    }

    @Override
    public long changeOrder() {
        return changeOrder;
    }

    @Override
    public String changeName() {
        return changeName;
    }

    public long getChangeOrder() {
        return changeOrder;
    }

    public String getChangeName() {
        return changeName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AbstractChange{");
        sb.append("changeOrder=").append(changeOrder);
        sb.append(", changeName='").append(changeName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractChange that = (AbstractChange) o;

        if (changeOrder != that.changeOrder) return false;
        return changeName != null ? changeName.equals(that.changeName) : that.changeName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (changeOrder ^ (changeOrder >>> 32));
        result = 31 * result + (changeName != null ? changeName.hashCode() : 0);
        return result;
    }
}
