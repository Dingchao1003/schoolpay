package com.dingchao.schoolpay.test;

import com.dingchao.schoolpay.test.implement.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
public class controller {


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
        mv.setViewName("/demo/greeting");
        mv.addObject("title","欢迎使用Thymeleaf!");
        return mv;
    }

}
