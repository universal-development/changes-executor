package com.unidev.changesexecutor.core;

import com.unidev.changesexecutor.model.Change;
import com.unidev.changesexecutor.model.ChangeContext;
import com.unidev.changesexecutor.model.ChangeExecutionResult;

import java.util.*;

/**
 * Service for processing changes
 */
public class ChangesCore {

    private static final Comparator<Change> CHANGE_COMPARATOR = new Comparator<Change>() {
        @Override
        public int compare(Change o1, Change o2) {
            return Long.compare(o1.changeOrder(), o2.changeOrder());
        }
    };

    private Set<Change> changes = new TreeSet<>(CHANGE_COMPARATOR);

    private ChangeResultStorage changeResultStorage;

    public ChangesCore(ChangeResultStorage changeResultStorage) {
        this.changeResultStorage = changeResultStorage;
    }

    /**
     * Add change to change list
     */
    public void addChange(Change change) {
        changes.add(change);
    }

    /**
     * Fetch available changes
     * @return
     */
    public Set<Change> fetchChanges() {
        return Collections.unmodifiableSet(changes);
    }

    /**
     * Execute current changes
     * @param changeContext
     */
    public void executeChanges(ChangeContext changeContext) {
        for(Change change : changes) {

            if (changeResultStorage.existChangeResult(change.changeName())) {
                if (changeResultStorage.fetchResult(change.changeName()).isSuccess()) {
                    continue;
                }
            }
            try {
                change.execute(changeContext);
                ChangeExecutionResult changeExecutionResult = new ChangeExecutionResult(change,
                        ChangeExecutionResult.Result.SUCCESS,  "Success");
                changeResultStorage.persistResult(changeExecutionResult);
            }catch (Exception e) {
                ChangeExecutionResult changeExecutionResult = new ChangeExecutionResult(change,
                        ChangeExecutionResult.Result.ERROR,
                        e.getMessage());
                changeResultStorage.persistResult(changeExecutionResult);
                throw new ChangeExecutionException(e);
            }
        }
    }

}
