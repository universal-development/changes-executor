package com.unidev.changesexecutor.core;

import com.unidev.changesexecutor.model.ChangeExecutionResult;

import java.util.Collection;

/**
 * Storage for change execution results
 */
public interface ChangeResultStorage {

    Collection<ChangeExecutionResult> listResults();

    ChangeExecutionResult fetchResult(long changeId);

    ChangeExecutionResult persistResult(ChangeExecutionResult changeExecutionResult);

}
