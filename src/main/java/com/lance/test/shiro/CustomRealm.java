package com.lance.test.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    private Map<String, String> userInfoMap;
    private Set<String> roleInfos;
    private Set<String> permissionInfos;


    public CustomRealm() {
        userInfoMap = new HashMap<>();
        userInfoMap.put("root", "asdzxc");
        userInfoMap.put("lance", "150920ccedc34d24031cdd3711e43310");

        roleInfos = new HashSet<>();
        roleInfos.add("admin");
        roleInfos.add("guest");

        permissionInfos = new HashSet<>();
        permissionInfos.add("blog:read");
        permissionInfos.add("blog:comment");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // Get username
        String username = (String) principals.getPrimaryPrincipal();

        // Get roles and permissions
        Set<String> roles = getRoleInfoByUsername(username);
        Set<String> permissions = getPermissionInfoByUsername(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);

        return info;
    }

    private Set<String> getPermissionInfoByUsername(String username) {
        return permissionInfos;
    }

    private Set<String> getRoleInfoByUsername(String username) {
        return roleInfos;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // Get username
        String username = (String) token.getPrincipal();

        // Query user's password
        String correctPassword = getPasswordByUsername(username);
        if (null == correctPassword) {
            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, correctPassword, "CustomRealm");
        info.setCredentialsSalt(new SimpleByteSource("321"));

        return info;
    }

    private String getPasswordByUsername(String username) {
        return userInfoMap.get(username);
    }
}
