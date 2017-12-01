package com.lance.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lance
 */
public class ShiroDemo {
    private static final Logger LOG = LoggerFactory.getLogger(ShiroDemo.class);

    public static void main(String[] args) {
        LOG.info("Shiro demo");

        LOG.info("Init security manager");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        LOG.info("Who am I?");
        Subject currentUser = SecurityUtils.getSubject();

        LOG.info("What can I do?");
        Session session = currentUser.getSession();
        session.setAttribute("aKey", "someValue");

        LOG.info("Check authentication...");
        if (!currentUser.isAuthenticated()) {
            LOG.info("Ready to log in");
            UsernamePasswordToken token = new UsernamePasswordToken("root", "112");
            token.setRememberMe(true);
            currentUser.login(token);
        }
        LOG.info("User[{}] log in", currentUser.getPrincipal());

        LOG.info("Check role");
        if (currentUser.hasRole("admin")) {
            System.out.println("Hello admin");
        } else if (currentUser.hasRole("guest")) {
            System.out.println("Hello guest");
        } else {
            System.out.println("get out");
        }

        LOG.info("Check permission");
        if (currentUser.isPermitted("gg:hj")) {
            System.out.println("Yes, you are permitted to do sth");
        }

    }
}
