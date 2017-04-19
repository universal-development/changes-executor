package com.unidev.changesexecutor.model;

import java.util.HashMap;

/**
 * Change execution context, used to bypass different variables to changes
 */
public class ChangeContext extends HashMap {

    public <T> T fetch(String key, T defaultValue) {
        if (!super.containsKey(key)) {
            return defaultValue;
        }
        return (T) get(key);
    }

}
