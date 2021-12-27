package com.lance.test.shiro;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 内存Realm
 *
 * @author Lance
 * @since 2021/12/27
 */
public class MemoryRealm extends AuthorizingRealm {

    /** 账号 */
    private final Map<String, String> accountMap = new HashMap<>();

    /** 角色 */
    private final Map<String, Set<String>> roleMap = new HashMap<>();

    /** 权限 */
    private final Map<String, Set<String>> permMap = new HashMap<>();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String account = (String) principals.getPrimaryPrincipal();
        Set<String> roles = roleMap.get(account);
        Set<String> perms = new HashSet<>();
        for (String role : roles) {
            Set<String> ps = permMap.get(role);
            if (CollectionUtils.isNotEmpty(ps)) {
                perms.addAll(ps);
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = (String) token.getPrincipal();
        if (!StringUtils.hasLength(account)) {
            return null;
        }

        String password = accountMap.get(account);
        return new SimpleAuthenticationInfo(account, password, getName());
    }

    /**
     * 添加账号
     *
     * @param account  账号
     * @param password 密码
     * @param roles    角色
     */
    public void addAccount(String account, String password, String... roles) {
        Objects.requireNonNull(account);
        Objects.requireNonNull(password);

        accountMap.put(account, password);
        Set<String> roleSet = new HashSet<>(Arrays.asList(roles));
        roleMap.put(account, roleSet);
    }

    /**
     * 添加角色
     *
     * @param role  角色
     * @param perms 权限
     */
    public void addRole(String role, String... perms) {
        Objects.requireNonNull(role);

        Set<String> permSet = new HashSet<>(Arrays.asList(perms));
        permMap.put(role, permSet);
    }
}
