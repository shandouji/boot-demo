package com.klayiu.bootdemo.entity;

import com.klayiu.bootdemo.base.BaseEntity;

import java.util.Date;

/**
 * @author 刘凯
 * @create 2020-04-18 22:06
 *
 *
 *
 * 资源
 *
 */
public class Resource extends BaseEntity {


    private static final long serialVersionUID = -7600295073301771504L;
    private Integer id;

    private String code;

    private String name;

    private Integer parentId;

    private String uri;

    private Short type;

    private String method;

  //  private String icon;

    private Short status;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
