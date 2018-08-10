package com.dingchao.schoolpay.shrio.service;

import com.dingchao.schoolpay.shrio.persistence.UserMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/8/9
 * \* Time: 下午11:56
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class SysUserService implements InitializingBean {

    @Autowired
  private   UserMapper userMapper;


    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
