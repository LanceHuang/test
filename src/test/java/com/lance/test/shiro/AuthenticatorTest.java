package com.lance.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class AuthenticatorTest {

    private SimpleAccountRealm realm;

    @Before
    public void before() {
        realm = new SimpleAccountRealm();
        realm.addAccount("Lance", "123");
    }

    @Test
    public void test() {
        SecurityManager securityManager = new DefaultSecurityManager();
        ((DefaultSecurityManager) securityManager).setRealm(realm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("Lance", "123");
        subject.login(token);
        System.out.println("isAuthenticated: " + subject.isAuthenticated());

        subject.logout();
    }

}
