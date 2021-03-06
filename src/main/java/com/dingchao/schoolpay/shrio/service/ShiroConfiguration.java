package com.dingchao.schoolpay.shrio.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/8/9
 * \* Time: 下午2:31
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
@Service
public class ShiroConfiguration {



    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("shrioRealm") ShrioRealm shrioRealm){
        SecurityManager securityManager=new DefaultWebSecurityManager();
        ((DefaultWebSecurityManager) securityManager).setRealm(shrioRealm);
        return securityManager;
    }

    /**
     * 过滤器工厂，配置shrio的针对哪些属性进行拦截等
     * @param securityManager
     * @return
     */


    @Bean("shirFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filterMap=new LinkedHashMap<>();


        //退出过滤器
        filterMap.put("/logout","logout");


        filterMap.put("/index","anon");
        filterMap.put("/login","anon");
        filterMap.put("/checklogin","anon");

        //静态资源无需权限访问
        filterMap.put("/js/**","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/img/**","anon");




        filterMap.put("/**","authc");

        shiroFilterFactoryBean.setLoginUrl("/login");

        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }


    /**
     * 加密方式用户名加盐
     * @param name
     * @param pwd
     * @return
     */
    public static String Encryp(String name,String pwd){
        Object result = new SimpleHash("MD5",pwd,name,1024);
        return result.toString();
    }



}
