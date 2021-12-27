package com.lance.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2021/12/27
 */
class MemoryRealmTest {

    private final MemoryRealm realm = new MemoryRealm();

    @BeforeEach
    void beforeEach() {
        realm.addAccount("lance", "123456", "admin");
        realm.addRole("admin", "*"); // yes
//        realm.addRole("admin", "article:*"); // yes
//        realm.addRole("admin", "article:read"); // yes
//        realm.addRole("admin", "post:read"); // no
    }

    @Test
    public void test() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);

        // 登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lance", "123456");
        subject.login(token);
        System.out.println("isAuth: " + subject.isAuthenticated());
        System.out.println("hasRole: " + subject.hasRole("anonymous"));
        System.out.println("hasRole: " + subject.hasRole("admin"));
        System.out.println("isPermit: " + subject.isPermitted("article:read"));
    }
}