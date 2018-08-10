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
public class Testcontroller {




    @Autowired
    private UserMapper userMapper;



    @ResponseBody
    @RequestMapping("/dingchao/test")
    public String test(HttpServletRequest req){
        System.out.println(req.getParameter("dingchao"));
//        Map<String,Object> map=userMapper.info();
//        System.out.println(map);
        return "hello world";

    }

    @RequestMapping(value = "/greeting")
    public ModelAndView test(ModelAndView mv) {
        System.out.println("进入页面");

        System.out.println(ShiroConfiguration.Encryp("dingchao","123456"));
        User u=new User();
        u.setUsername("dingchao");
        u.setUserpwd("3b083285265e5d2e735f1895dbf0f270");
        User u2=userMapper.info(u);
        System.out.println(u2);
        mv.setViewName("/demo/greeting");
        mv.addObject("title","欢迎使用Thymeleaf!");
        return mv;
    }


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
    @RequestMapping(value = "/userlogin")
    public ModelAndView login(ModelAndView mv){
        mv.setViewName("/demo/login");
        return  mv;
    }

}
