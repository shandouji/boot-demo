package com.klayiu.bootdemo.service;

import com.klayiu.bootdemo.entity.AuthAccountLog;

/**
 * @author 刘凯
 * @create 2020-04-15 22:34
 *
 *
 */
public interface LogService {

    boolean insert(AuthAccountLog account);

    void deleteAll();
}
