/*
 * description
 */
package com.ztk.test.dynamic.datasource.service;

import com.ztk.test.dynamic.datasource.mapper.TestUserMapper;
import com.ztk.test.dynamic.datasource.mapper.UserMapper;
import com.ztk.test.dynamic.datasource.mapper2.TestMapper;
import com.ztk.test.dynamic.datasource.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@Service
public class TestService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TestUserMapper testUserMapper;

    @Resource
    private TestMapper testMapper;

    public User test1() {
        return testUserMapper.select();
    }

    public User test2() {
        return testMapper.select();
    }

    public User test3() {
        return userMapper.select();
    }

    public User test4(int id) {
        return userMapper.getById(id);
    }

}
