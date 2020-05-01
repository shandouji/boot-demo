package com.klayiu.bootdemo.service;

import com.klayiu.bootdemo.entity.AuthAccountLog;

import java.util.List;

/**
 * @author klayiu
 * @create 2020-04-15 22:34
 *
 *
 */
public interface LogService {

    boolean insert(AuthAccountLog account);

    void deleteAll();

    List<AuthAccountLog> findAll();

    AuthAccountLog findById(Integer id);
}
