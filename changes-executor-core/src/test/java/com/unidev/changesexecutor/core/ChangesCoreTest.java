package com.unidev.changesexecutor.core;

import com.unidev.changesexecutor.model.Change;
import com.unidev.changesexecutor.model.ChangeContext;
import com.unidev.changesexecutor.model.ChangeExecutionResult;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ChangesCoreTest {

    ChangesCore changesCore;

    @Before
    public void setup() {
        changesCore = new ChangesCore(new ChangeResultStorage() {

            Map<String, ChangeExecutionResult> results = new HashMap<>();

            @Override
            public Collection<ChangeExecutionResult> listResults() {
                return results.values();
            }

            @Override
            public boolean existChangeResult(String changeName) {
                return results.containsKey(changeName);
            }

            @Override
            public ChangeExecutionResult fetchResult(String changeName) {
                return results.get(changeName);
            }

            @Override
            public ChangeExecutionResult persistResult(ChangeExecutionResult changeExecutionResult) {
                results.put(changeExecutionResult.getChange().changeName(), changeExecutionResult);
                return changeExecutionResult;
            }
        });
    }

    @Test
    public void testSequenceExecution() {

        changesCore.addChange(new Change() {
            @Override
            public long changeOrder() {
                return 1;
            }

            @Override
            public String changeName() {
                return "change1";
            }

            @Override
            public void execute(ChangeContext changeContext) {
                System.out.println(changeName());
            }
        });

        changesCore.addChange(new Change() {
            @Override
            public long changeOrder() {
                return 2;
            }

            @Override
            public String changeName() {
                return "change2";
            }

            @Override
            public void execute(ChangeContext changeContext) {
                System.out.println(changeName());
            }
        });

        ChangeContext changeContext = new ChangeContext();

        changesCore.executeChanges(changeContext);

    }

}
