package com.klayiu.bootdemo.service.impl;


import com.klayiu.bootdemo.entity.User;
import com.klayiu.bootdemo.mapper.UserMapper;
import com.klayiu.bootdemo.service.UserService;
import com.klayiu.bootdemo.support.factory.LogTaskFactory;
import com.klayiu.bootdemo.support.manager.LogExeManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author klay
 * @create 2020-04-11 15:13
 */

@Service
public class UserServiceImpl implements UserService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> user = userMapper.getAll();
        return user;
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public boolean insert(User user) {
        int num = userMapper.add(user);
        return num == 1?Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public User getName(String userName) {
        User user = userMapper.getName(userName);
        return user;
    }

    @Override
    public User getPassWord(String passWord) {
        User user = userMapper.getPassWord(passWord);
        return user;
    }
}
