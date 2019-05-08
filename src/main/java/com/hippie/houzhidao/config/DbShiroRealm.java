package com.hippie.houzhidao.config;

import com.hippie.houzhidao.domain.UserInfo;
import com.hippie.houzhidao.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 39239
 * @Date 2019/5/6 21:01
 * @Package com.hippie.houzhidaoadmin.config
 * @Description:
 */

public class DbShiroRealm extends AuthorizingRealm {

    private static final String encryptSalt = "F12839WhsnnEV$#23b";
    @Autowired
    private UserService userService;

    public DbShiroRealm(UserService userService) {
        this.userService = userService;
//        this.setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userpasswordToken = (UsernamePasswordToken)token;
        String username = userpasswordToken.getUsername();
        UserInfo user = userService.getUserInfo(username);
        if(user == null) {
            throw new AuthenticationException("用户名或者密码错误");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), "dbRealm");
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserInfo user = (UserInfo) principals.getPrimaryPrincipal();
        List<String> roles = userService.getUserRoles(user.getUserName());
        simpleAuthorizationInfo.addRoles(roles);
        return simpleAuthorizationInfo;
    }


}
