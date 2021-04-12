package com.lance.test.common.pool2;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lance
 * @since 2021/4/12
 */
public class TConnectionFactory extends BasePooledObjectFactory<TConnection> {

    private final AtomicInteger nextId = new AtomicInteger();

    @Override
    public TConnection create() throws Exception {
        return new TConnection(nextId.incrementAndGet());
    }

    @Override
    public PooledObject<TConnection> wrap(TConnection obj) {
        return new DefaultPooledObject<>(obj);
    }

    @Override
    public boolean validateObject(PooledObject<TConnection> p) {
        return p.getObject().isActive();
    }

    @Override
    public void activateObject(PooledObject<TConnection> p) throws Exception {
        p.getObject().init();
    }

    @Override
    public void passivateObject(PooledObject<TConnection> p) throws Exception {
        // 钝化操作，如：提交、回滚事务
    }

    @Override
    public void destroyObject(PooledObject<TConnection> p) throws Exception {
        p.getObject().close();
    }

}
