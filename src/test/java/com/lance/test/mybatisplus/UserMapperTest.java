package com.lance.test.mybatisplus;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.lance.test.mybatisplus.mapper.UserMapper;
import com.lance.test.mybatisplus.model.User;
import com.lance.test.mybatisplus.model.UserInfo;
import com.lance.test.mybatisplus.model.UserInfoTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserMapperTest.class)
@Configuration
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test");
        ds.setUsername("root");
        ds.setPassword("123456");
        return ds;
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setTypeHandlers(new UserInfoTypeHandler(UserInfo.class));
        return mybatisSqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.lance.test.mybatisplus");
        return configurer;
    }

    @Test
    public void test() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

//        User newUser = new User();
//        newUser.setId(6L);
//        newUser.setName("adfadf");
////        newUser.setData(10);
//        userMapper.insert(newUser);
    }
}