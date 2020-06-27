package com.klayiu.bootdemo.entity;

import com.klayiu.bootdemo.base.BaseEntity;

import java.util.Date;

/**
 * @author 刘凯
 * @create 2020-04-13 10:57
 *
 * 角色
 *
 * RBAC 权限模型（核心基于角色）
 *
 *
 * @1 url 资源拦截
 * @2 菜单级别权限
 * @3 按钮级别权限
 *
 */
public class Role extends BaseEntity {


    private static final long serialVersionUID = -212623450814460016L;
    private Integer id;

    private String code;

    private String name;

    private String status;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
