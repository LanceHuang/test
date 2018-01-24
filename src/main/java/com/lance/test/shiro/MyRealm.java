package com.lance.test.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * @author Lance
 * @date 2018/1/22 11:00
 */
public class MyRealm extends AuthorizingRealm {

    private String testData;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println(principals);
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("testData: " + testData);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();

        SimplePrincipalCollection collection = new SimplePrincipalCollection();
        authenticationInfo.setCredentials(token.getCredentials());

        System.out.println(token);
        return authenticationInfo;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }
}
