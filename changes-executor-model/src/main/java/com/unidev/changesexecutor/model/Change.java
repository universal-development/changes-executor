package com.unidev.changesexecutor.model;

/**
 * Change which should be executed
 */
public interface Change {

    /**
     * Fetch change execution order
     */
    long changeOrder();

    /**
     * Fetch change name
     */
    String changeName();

    /**
     * Execute change on provided context
     * @param changeContext
     */
    void execute(ChangeContext changeContext);

}
