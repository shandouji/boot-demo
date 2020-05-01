package com.klayiu.bootdemo.service.impl;

import com.klayiu.bootdemo.entity.AuthAccountLog;
import com.klayiu.bootdemo.mapper.AccountLogMapper;
import com.klayiu.bootdemo.service.LogService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘凯
 * @create 2020-04-15 22:35
 *
 *
 */

@Service
public class LogServiceImpl  implements LogService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    AccountLogMapper logMapper;

    @Override
    public boolean insert(AuthAccountLog account) {
        int num = logMapper.insertSelective(account);
        return num == 1?Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public void deleteAll() {
        logMapper.deleteAll();;
    }

    @Override
    public List<AuthAccountLog> findAll() {
        LOGGER.info("查询全部日志信息");
        return logMapper.getAll();
    }

    @Override
    public AuthAccountLog findById(Integer id) {
        return logMapper.findLogById(id);
    }
}
