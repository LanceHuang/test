package com.lance.test.common.concurrent;

import java.util.concurrent.Executor;

public class SimpleSyncExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
