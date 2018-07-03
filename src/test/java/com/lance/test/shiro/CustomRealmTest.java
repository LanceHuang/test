package com.lance.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class CustomRealmTest {
    private CustomRealm realm;

    @Before
    public void before() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1);

        realm = new CustomRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
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
