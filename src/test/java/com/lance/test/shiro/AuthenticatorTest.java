package com.lance.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

/**
 * 认证测试
 *
 * @author Lance
 * @since 2021/12/24
 */
public class AuthenticatorTest {

    @Test
    public void test() {
        // 添加账号
        SimpleAccountRealm realm = new SimpleAccountRealm();
        realm.addAccount("lance", "123456");

        // 构建
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);

        // 设置安全管理，以后可通过SecurityUtils快速操作
        SecurityUtils.setSecurityManager(securityManager);

        // 获取主体
        Subject subject = SecurityUtils.getSubject();

        // 登录
        UsernamePasswordToken token = new UsernamePasswordToken("lance", "123456");
        subject.login(token);
        System.out.println("isAuth: " + subject.isAuthenticated());

        // 登出
        subject.logout();
        System.out.println("isAuth: " + subject.isAuthenticated());
    }
}
