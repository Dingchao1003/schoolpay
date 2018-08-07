package com.dingchao.schoolpay.test;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class controller {

    @ResponseBody
    @RequestMapping("/dingchao/test")
    public String test(HttpServletRequest req){
        System.out.println(req.getParameter("dingchao"));
        return "hello world";

    }

}
