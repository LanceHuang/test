package com.lance.test.sharding;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.core.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MasterSlaveDataSourceConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource masterDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/common");
        ds.setUsername("root");
        ds.setPassword("");
        return ds;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource slaveDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3307/common");
        ds.setUsername("root");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        MasterSlaveRuleConfiguration masterSlaveRuleConfiguration =
                new MasterSlaveRuleConfiguration(
                        "ds_master_slave",
                        "ds_master",
                        Arrays.asList("ds_slave_0"));

        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds_master", masterDataSource());
        dataSourceMap.put("ds_slave_0", slaveDataSource());

        return MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfiguration, new HashMap<>());
    }
}
