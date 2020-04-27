package com.klayiu.bootdemo.mapper;

import com.klayiu.bootdemo.entity.AuthAccountLog;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * @author 刘凯
 * @create 2020-04-15 22:18
 *
 * 用户日志相关操作方法
 */

@Repository
public interface AccountLogMapper {


    int insertSelective(AuthAccountLog authAccountLog) throws DataAccessException;


    void deleteAll() throws DataAccessException;
}
