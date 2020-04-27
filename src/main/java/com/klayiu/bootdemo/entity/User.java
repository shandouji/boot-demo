package com.klayiu.bootdemo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.klayiu.bootdemo.base.BaseEntity;

import java.util.Date;

/**
 * @author 刘凯
 * @create 2020-04-11 9:03
 *
 *
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = -1160281146417256049L;
    private Integer id;
  // @ExcelProperty(value = "姓名" ,index = 1)
    private String userName;
    private String passWord;
    @ExcelProperty(value = "邮件" ,index = 1)
    private String email;
    @ExcelProperty(value = "性别" ,index = 2)
    private String sex;
    @ExcelProperty(value = "状态" ,index = 3)
    private String status;
    @ExcelProperty(value = "电话" ,index = 4)
    private String telePhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", telePhone='" + telePhone + '\'' +
                '}';
    }

}
