package com.klayiu.bootdemo.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

/**
 * @author 刘凯
 * @create 2020-04-11 9:04
 *
 *
 * shiro
 */
public class BaseController {

    public Subject getSubject(){

        return SecurityUtils.getSubject();
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

}
