package com.klayiu.bootdemo.entity;

import com.klayiu.bootdemo.base.BaseEntity;

/**
 * @author 刘凯
 * @create 2020-04-18 21:51
 *
 *
 *
 * UserRole 对应 user表和role表
 */
public class UserRole extends BaseEntity {
    private static final long serialVersionUID = -852288119396855266L;

    private Integer id;

    // 关联User 表
    private String userId;

    //关联roleId
    private String roleId;

    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
