package com.lance.test.common.pool2;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2021/4/12
 */
public class CommonPool2Test {

    @Test
    public void test() throws Exception {
        GenericObjectPoolConfig<TConnection> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMinIdle(1);
        poolConfig.setMaxIdle(2);
        poolConfig.setMaxTotal(4);
        TConnectionFactory factory = new TConnectionFactory();

        GenericObjectPool<TConnection> pool = new GenericObjectPool<>(factory, poolConfig);
        TConnection tConnection = pool.borrowObject();
        tConnection.use();
        pool.returnObject(tConnection);
        pool.close();
    }
}
