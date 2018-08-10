package com.dingchao.schoolpay.shrio.service;

import com.dingchao.schoolpay.shrio.entity.User;
import com.dingchao.schoolpay.shrio.persistence.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/8/9
 * \* Time: 下午2:04
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
@Component("shrioRealm")
public class ShrioRealm extends AuthorizingRealm {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserMapper userMapper;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return null;


    }

    //认证登陆过程
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;


        String username = usernamePasswordToken.getUsername();
        char[] pwd = usernamePasswordToken.getPassword();
        User in = new User();
        in.setUsername(username);
        in.setUserpwd(String.copyValueOf(pwd));
        System.out.println(userMapper == null);
        User user = userMapper.info(in);

        if (user == null) {
            throw new AccountException("用户名或密码不存在");
        }
        return new SimpleAuthenticationInfo(user, String.copyValueOf(pwd), getName());

    }
}
