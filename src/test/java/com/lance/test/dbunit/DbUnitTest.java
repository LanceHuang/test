package com.lance.test.dbunit;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dbunit/spring-dbunit.xml")
@DbUnitConfiguration(databaseConnection = "h2DataSource") //默认读spring中的dataSource
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class DbUnitTest {

    @Resource
    private SessionFactory sessionFactory;

    private Session session;
    private Transaction transaction;

    @Before
    public void before() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void after() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/user_pre.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(value = "classpath:dbunit/user_post.xml")
//    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void test() {
//        User user = (User) session.get(User.class, 2);
//        System.out.println(user);

        System.out.println(session.createQuery("from com.lance.test.dbunit.User").list().size());
//        User user2 = (User) session.get(User.class,2);
//        session.delete(user2);

//        User user1 = (User) session.get(User.class,1);
//        user1.setName("De ma xi ya ");
//        session.update(user1);
    }
}
