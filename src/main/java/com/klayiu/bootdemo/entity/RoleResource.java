package com.klayiu.bootdemo.entity;

import com.klayiu.bootdemo.base.BaseEntity;

import java.util.Date;

/**
 * @author 刘凯
 * @create 2020-04-18 22:01
 *
 *
 *
 * 角色绑定资源
 *
 */
public class RoleResource extends BaseEntity {


    private static final long serialVersionUID = 4145614482887411799L;
    private Integer id;

    //关联角色
    private Integer roleId;

    //关联资源Id
    private Integer resourceId;

    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
