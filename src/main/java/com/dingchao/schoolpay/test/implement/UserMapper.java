package com.dingchao.schoolpay.test.implement;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/8/7
 * \* Time: 上午10:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Mapper
public interface UserMapper {

    Map<String,Object> info();
}
