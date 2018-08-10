package com.dingchao.schoolpay.shrio.service;

import com.dingchao.schoolpay.shrio.entity.User;
import org.apache.shiro.SecurityUtils;
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


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       User user= (User) SecurityUtils.getSubject().getPrincipal();
       return null;


    }

    //认证登陆过程
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;


        String username = usernamePasswordToken.getUsername();
        char[] pwd=usernamePasswordToken.getPassword();
        System.out.println(username);
        System.out.println(pwd);
        User user=null;

        if(username.equals("dingchao")){
//            user=new User("dingc","1","dc");
        }else {
            throw new AccountException("账号密码不对");
        }

        return new SimpleAuthenticationInfo(user, "1", getName());

    }
}
