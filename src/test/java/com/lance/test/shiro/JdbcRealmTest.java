package com.lance.test.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class JdbcRealmTest {
    private JdbcRealm realm;
    private DruidDataSource dataSource;

    @Before
    public void before() throws SQLException {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/common");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.init();

        realm = new JdbcRealm();
        realm.setDataSource(dataSource);
        realm.setPermissionsLookupEnabled(true);
        realm.setAuthenticationQuery("select password from t_user where username = ?");
        realm.setUserRolesQuery("select role_name from t_user_role where username = ?");
        realm.setPermissionsQuery("select permission from t_roles_permission where role_name = ?");
    }

    @After
    public void after() {
        dataSource.close();
    }

    @Test
    public void test() {
        SecurityManager securityManager = new DefaultSecurityManager();
        ((DefaultSecurityManager) securityManager).setRealm(realm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("lance", "123");
        subject.login(token);
        System.out.println("isAuthenticated: " + subject.isAuthenticated());
        subject.checkRole("guest");
        subject.checkPermissions("blog:read", "blog:comment");

        subject.logout();
    }
}
