package com.unidev.changesexecutor.core;

import com.unidev.changesexecutor.model.Change;
import com.unidev.changesexecutor.model.ChangeContext;

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
            change.execute(changeContext);
        }
    }

}
