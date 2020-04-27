package com.klayiu.bootdemo.mapper;

import com.klayiu.bootdemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 刘凯
 * @create 2020-04-11 15:10
 *
 *
 * 用户相关操作方法
 */

@Repository
public interface UserMapper {

    User getUserById(String id);

    List<User> getAll();

    void deleteById(String id);

    int add(User user);

    User getName(String userName);

    User getPassWord(String passWord);
}
