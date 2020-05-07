package com.klayiu.bootdemo.realm;

import com.klayiu.bootdemo.entity.User;
import com.klayiu.bootdemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


/**
 * @author 刘凯
 * @create 2020-04-11 21:49
 *
 * 自定义实现Realm
 */
public class CustomRealm extends AuthorizingRealm {


     // 注入UserService
    @Autowired
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);

    /**
     *
     * Shiro  支持三种方式的授权
     * 编程式
     * 注解式
     * JSP标签
     *
     *
     *
     * @param principals
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("执行授权逻辑");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //step 1. 从PrincipalCollection 中来获取登录的用户信息

        //step 2.利用登录的用户信息来当前用户的角色或者权限（可能需要查询数据库）

        //step 3.创建 SimpleAuthenticationInfo ，并设置其roles 属性

        //step 4 . 返回 SimpleAuthenticationInfo 对象


        // 创建SimpleAuthorizationInfo , 并设置roles
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();


        //如果想在前台显示 欢迎xxx 登陆, 在前台页面使用  shiro 标签
        return info;
    }

    /**
     * 用户认证
     * @param token
     *          用户认证身份
     * @return
     *          AuthenticationInfo 子类 身份认证信息
     * @throws AuthenticationException
     *          身份认证相关异常
     *
     *
     * 认证说明书
     *
     *          login() 登录之后执行认证
     *          用户名密码进行判断
     *
     *
     *          @
     *          1 判断用户名密码是否正确
     *          2 判断用户状态(无效用户,用户锁定)
     *
     *
     *
     *          用盐值对密码进行 MD5 进行加密
     *
     *
     *
     *
     *          //step 3 调用数据库中的方法，从数据中查询userName 对应的用户记录
     *          //step 4 若用户不存在，直接抛出异常
     *          // 前台密码明文转换成密文
     *          // 根据前台传进来的密码,去数据库里面查询
     *          //  User u = userService.getPassWord("b998693862d10db1bcdfa3767cb9ca1c");
     *          // String passWord = "b998693862d10db1bcdfa3767cb9ca1c";
     *
     *          // step principal : 认证的实体信息，可以是UserName, 也可以是数据表对应的用户实体类对象。
     *          //step 6 realmName ,当前realm 对象的name,调用父类的getName() 方法即可。
     *
     *           // 1> why 为什么用MD5 盐值加密 ？
     *           // 2> 如何做到
     *           // @ 1 doGetAuthenticationInfo 方法返回值创建SimpleAuthenticationInfo 对象, 需要使用比较复杂构造器。
     *           // @ 2 ByteSource.Util.bytes 计算盐值
    *           // @ 3 盐值需要唯一， 一般情况下使用随机数，userId
    *           // @ 3 使用new suimpleHash() ,来计算盐值加密后的密码的值。
    *           // 现在这样写是不对的， 需要进行密码比对 ，upToken 前台传过来的方法，SimpleAuthenticationInfo 是从数据库中查询出来存到 SimpleAuthenticationInfo 里面
    *
    *           // String passWord = "b998693862d10db1bcdfa3767cb9ca1c";
    *           //user.getPassWord() 这个密码不应该是从数据里面查询出来之后，放在SimpleAuthenticationInfo 里面的,应该是从 前台传过来经过 HashedCredentialsMatcher 加密 字符串 传进去。
    *           // return new SimpleAuthenticationInfo(principal,user.getPassWord(),salt,getName());
     *
     *
     *          通过 AuthenticatingRealm 的 credentialsMatcher 属性来进行密码的比对。
     *
     *          无需自己进行密码比对 ,shiro 内部已经帮我们完成了密码比对的功能。
     *
     *
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LOGGER.info("执行认证逻辑");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        User user = userService.getName(upToken.getUsername());
        if(user == null){
            throw new UnknownAccountException("用户不存在");
        }
        Object principal = upToken.getUsername();
        Object pwd = user.getPassWord();
        String realmName = getName();
        ByteSource salt = ByteSource.Util.bytes(upToken.getUsername()); //盐值
        SimpleAuthenticationInfo info = null;
        subject.getSession().setAttribute("user",user);  // 用户信息存储到Session 中
        info = new SimpleAuthenticationInfo(principal,pwd,salt,realmName);
        //new SimpleAuthenticationInfo(principal,pwd,realmName);
        return info;
    }
}
