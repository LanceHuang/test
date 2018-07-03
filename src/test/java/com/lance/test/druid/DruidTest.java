package com.lance.test.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DruidTest {

    private DruidDataSource dataSource;

    @Before
    public void before() throws SQLException {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/common");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.init();
    }

    @After
    public void after() {
        dataSource.close();
    }

    @Test
    public void test() throws SQLException {
        for (int i = 0; i < 10; i++) {
            Connection conn = dataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("select count(*) from t_data");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                System.out.println("Result: " + rs.getString(1));
            }
            rs.close();
            stat.close();
            //It'll be blocked if it's not work
            conn.close();
        }

    }
}
