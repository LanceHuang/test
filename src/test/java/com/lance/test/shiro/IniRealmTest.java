package com.lance.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

public class IniRealmTest {
    @Test
    public void test() {
        IniRealm realm = new IniRealm("classpath:shiro.ini");

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("guest", "12346");
        subject.login(token);
        System.out.println("isAuth: " + subject.isAuthenticated());
        System.out.println("hasRole: " + subject.hasRole("anonymous"));
        System.out.println("isPermit: " + subject.isPermitted("article:read"));
        subject.logout();
    }
}
