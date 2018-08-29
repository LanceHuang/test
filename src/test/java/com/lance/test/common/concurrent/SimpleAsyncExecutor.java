package com.lance.test.common.concurrent;

import java.util.concurrent.Executor;

public class SimpleAsyncExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
