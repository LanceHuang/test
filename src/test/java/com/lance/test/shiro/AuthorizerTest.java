package com.lance.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

/**
 * 授权测试
 *
 * @author Lance
 * @since 2021/12/24
 */
public class AuthorizerTest {

    @Test
    public void test() {
        SimpleAccountRealm realm = new SimpleAccountRealm();
        realm.addAccount("lance", "123456", "admin", "anonymous");

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        // 判断是否有相应的角色
        System.out.println("Before login hasRole: " + subject.hasRole("admin"));
        UsernamePasswordToken token = new UsernamePasswordToken("lance", "123456");
        subject.login(token);
        System.out.println("isAuth: " + subject.isAuthenticated());
        System.out.println("After login hasRole: " + subject.hasRole("admin"));
    }
}
