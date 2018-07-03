package com.lance.test.sharding;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.core.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.InlineShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShardingDataSourceConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource masterDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db_0");
        ds.setUsername("root");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public DataSource dataSource() throws SQLException {

        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("db_0", masterDataSource());


        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
        tableRuleConfiguration.setLogicTable("t_order");
        tableRuleConfiguration.setActualDataNodes("db_0.t_order_${0..1}");
        tableRuleConfiguration.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("id", "t_order_${id % 2}"));

        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);


        return ShardingDataSourceFactory.createDataSource(
                dataSourceMap,
                shardingRuleConfiguration,
                new HashMap<>(),
                new Properties()
        );
    }
}
