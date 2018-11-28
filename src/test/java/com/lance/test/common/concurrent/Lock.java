package com.lance.test.common.concurrent;

/**
 * @author Lance
 * @since 2018-11-16 11:27:38
 */
public interface Lock {

    void lock();

    boolean tryLock();

    void unlock();
}
