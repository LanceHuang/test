package com.lance.test.sharding;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ShardingDataSourceConfig.class)
public class ShardingTest {

    @Resource
    private DataSource dataSource;


    @Test
    public void test() {
        Connection conn = null;
        Statement stat = null;
        try {
            conn = dataSource.getConnection();
            stat = conn.createStatement();
            for (int i = 0; i < 10; i++)
                System.out.println(stat.execute("insert into t_order(id,name) values(" + i + ",'hi')"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(stat);
            closeQuietly(conn);
        }
    }

    private void closeQuietly(AutoCloseable resource) {
        if (null != resource) {
            try {
                resource.close();
            } catch (Exception e) {
                //Do nothing
            }
        }
    }
}
