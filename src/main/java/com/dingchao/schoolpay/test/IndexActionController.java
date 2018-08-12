package com.dingchao.schoolpay.test;

import com.dingchao.schoolpay.shrio.entity.User;
import com.dingchao.schoolpay.shrio.persistence.UserMapper;
import com.dingchao.schoolpay.shrio.service.ShiroConfiguration;
import com.dingchao.schoolpay.shrio.service.ShrioRealm;
import com.dingchao.schoolpay.test.implement.testUserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/8/7
 * \* Time: 上午9:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
public class IndexActionController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/index")
    public ModelAndView test(ModelAndView mv) {
        //给用户名加密处理
        //ShiroConfiguration.Encryp("dingchao","123456");
        User u=new User();
        u.setUsername("yjq");
        u.setUserpwd("018c6f96e1bf813648dfe15f66e88cd9");
        User u2=userMapper.info(u);

        mv.setViewName("/html/index");
        mv.addObject("title","欢迎使用Thymeleaf!");
        return mv;
    }


    @ResponseBody
    @RequestMapping(value = "/checklogin", method = RequestMethod.POST)
    public String login(String username, String password) {
        try {
            // 从SecurityUtils里边创建一个 subject
            Subject subject = SecurityUtils.getSubject();

            String pwd= ShiroConfiguration.Encryp(username,password);
            System.out.println(pwd);
            // 在认证提交前准备 token（令牌）
            UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
            // 执行认证登陆
            subject.login(token);

            User user=  (User) SecurityUtils.getSubject().getPrincipal();
            System.out.println(user);
            return "登陆成功";
            //根据权限，指定返回数据
        }catch (Exception e){
            e.printStackTrace();
            return "登陆失败";
        }

    }
    @RequestMapping(value = "/login")
    public ModelAndView login(ModelAndView mv){
        mv.setViewName("/html/login");
        return  mv;
    }

}
