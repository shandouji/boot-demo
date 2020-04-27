package com.klayiu.bootdemo.service;

import com.klayiu.bootdemo.entity.User;

import java.util.List;

/**
 * @author 刘凯
 * @create 2020-04-11 15:11
 */
public interface UserService {


    User getUserById(String id);

    List<User> findAll();

    void deleteById(String id);

    boolean insert(User user);

    User getName(String userName);

    User getPassWord(String passWord);


}
