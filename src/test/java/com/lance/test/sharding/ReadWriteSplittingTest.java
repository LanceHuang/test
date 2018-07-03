package com.lance.test.sharding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MasterSlaveDataSourceConfig.class)
public class ReadWriteSplittingTest {

    @Resource
    private DataSource dataSource;

    /**
     * Firstly, stop slave and insert data. Then, check master and slave. It must only appear in master
     */
    @Test
    public void testMaster() {
        Connection conn = null;
        Statement stat = null;
        try {
            conn = dataSource.getConnection();
            stat = conn.createStatement();
            System.out.println(stat.execute("insert into t_data values('memeda')"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(stat);
            closeQuietly(conn);
        }
    }

    /**
     * It must only output slave's data
     */
    @Test
    public void testSlave() {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from t_data");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(rs);
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
