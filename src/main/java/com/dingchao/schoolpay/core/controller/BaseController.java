package com.dingchao.schoolpay.core.controller;

import com.dingchao.schoolpay.shrio.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/8/9
 * \* Time: 下午4:42
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
public class BaseController {


    /**
     * 获取当前登录对象
     *
     * @return User:系统定义用户，自定义
     */
    protected static User getUser() {
        Object obj;

        obj = SecurityUtils.getSubject().getPrincipal();
        if (obj != null) {
            return (User) obj;
        }

        return null;


    }


}
