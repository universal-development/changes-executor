package com.unidev.changesexecutor.core;

import com.unidev.changesexecutor.model.AbstractChange;
import com.unidev.changesexecutor.model.Change;
import com.unidev.changesexecutor.model.ChangeContext;
import com.unidev.changesexecutor.model.ChangeExecutionResult;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

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

        final Map<String, Date> executions = new HashMap<>();

        changesCore.addChange(new Change() {
            @Override
            public long changeOrder() {
                return 10;
            }

            @Override
            public String changeName() {
                return "change1";
            }

            @Override
            public void execute(ChangeContext changeContext) {
                System.out.println(changeName());
                executions.put(changeName(), new Date());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        changesCore.addChange(new Change() {
            @Override
            public long changeOrder() {
                return 20;
            }

            @Override
            public String changeName() {
                return "change2";
            }

            @Override
            public void execute(ChangeContext changeContext) {
                System.out.println(changeName());
                executions.put(changeName(), new Date());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ChangeContext changeContext = new ChangeContext();

        changesCore.executeChanges(changeContext);

        Date change1 = executions.get("change1");
        Date change2 = executions.get("change2");

        assertThat( change2.compareTo(change1), is(1) );

    }

    @Test
    public void testMigrationWithExceptions() {
        final Map<String, Date> executions = new HashMap<>();

        changesCore.addChange(new AbstractChange(10, "Step 1") {
            @Override
            public void execute(ChangeContext changeContext) {
                System.out.println(changeName());
                executions.put(changeName(), new Date());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        changesCore.addChange(new AbstractChange(11, "Step 2") {
            @Override
            public void execute(ChangeContext changeContext) {
                throw new RuntimeException("Tomato");
            }
        });

        ChangeContext changeContext = new ChangeContext();

        try {
            changesCore.executeChanges(changeContext);
            fail();
        }catch (ChangeExecutionException e) {
            assertThat(executions.size(), is(1));
            assertThat(executions.containsKey("Step 1"), is(true));
        }
    }

    @Test(expected = ChangeAddException.class)
    public void testErrorOnAddingChangesWithSameOrder() {
        changesCore.addChange(new AbstractChange(666, "Step 1") {
            @Override
            public void execute(ChangeContext changeContext) {

            }
        });

        changesCore.addChange(new AbstractChange(666, "Step 2") {
            @Override
            public void execute(ChangeContext changeContext) {

            }
        });
    }

}
