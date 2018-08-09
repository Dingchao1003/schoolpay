package com.dingchao.schoolpay.shrio.service;

import com.dingchao.schoolpay.shrio.entity.user;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

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
public class ShrioRealm extends AuthorizingRealm {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    //认证，登录
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       return null;


    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;


        String username = usernamePasswordToken.getUsername();
        char[] pwd=usernamePasswordToken.getPassword();
        System.out.println(username);
        System.out.println(pwd);
        user user=null;

        if(username.equals("dingchao")){
            user=new user("dingc","1","dc");
        }else {
            throw new AccountException("账号密码不对");
        }

        return new SimpleAuthenticationInfo(user, "1", getName());

    }
}
