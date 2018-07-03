package com.lance.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class IniRealmTest {

    private IniRealm realm;

    @Before
    public void before() {
        realm = new IniRealm("classpath:shiro.ini");
    }

    @Test
    public void test() {
        SecurityManager securityManager = new DefaultSecurityManager();
        ((DefaultSecurityManager) securityManager).setRealm(realm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("guest", "gg");
        subject.login(token);
        System.out.println("isAuthenticated: " + subject.isAuthenticated());
        System.out.println("hasRole:" + subject.hasRole("guest"));
        subject.checkPermission("gg:read");

        subject.logout();
    }
}
