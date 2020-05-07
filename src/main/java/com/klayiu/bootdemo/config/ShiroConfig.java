package com.klayiu.bootdemo.config;

import com.klayiu.bootdemo.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author klay
 * @create 2020-04-11 20:33
 *
 *  spring boot 与shiro 整合 用于认证token，登录授权，权限
 *  shiro 配置类
 *
 *  关于shiro
 *
 *  1. Subject:用户主体(把操作交给SecurityManager)
 *  2. SecurityManager: 安全管理器(关联Realm)
 *  3. Realm: Shiro 连接数据的桥梁
 *
 *
 *  @ shiro 内置过滤器，可以实现权限相关的拦截器
 *          常用过滤器
 *         1 anon 无需认证，可以实现权限相关的拦截器
 *         2 authc 必须认证才可以访问
 *         3 user  使用remeberme (记住我)的功能可以直接访问
 *         4 perms 该资源必须得到资源权限才可以访问
 *         5 roles  该资源必须得到角色权限才可以访问
 *
 *
 *
 * @ shiro restful 风格请求方式， get/post / url httpMethod 请求头不一样
 *   针对这种情况需要重写 shiro PathMatchingFilterChainResolver 中的getChain 重新匹配 url 规则，
 *
 *
 *
 * @ shiro 实现无状态登录, 即服务器端无状态,服务器端不会存储会话 ,每次请求的时候都带上相应的用户名进行登录
 *
 *
 *
 *
 * =======================================
 *
 * 单点登录说明
 *
 *   实现单点登录 , shiro 官方 不建议使用shiro -cas ,shiro-cas模块的相关都被shiro标注为@Deprecated
 *   并且推荐使用的是 pac4j 来实现代替方案
 *
 *
 */

@Configuration
public class ShiroConfig {



    /**
     * filter ,设置对应的过滤和跳转 条件
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        //实例化shiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        Map<String,String> map = new HashMap<>();
        //对所有用户认证
      //  map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * realm 的认证管理
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }



    /**
     * 自定义realm 实现 ,将自己的验证方式加入容器
     * @return
     */
    @Bean
    public CustomRealm myRealm(){
        CustomRealm customRealm = new CustomRealm();
       customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
       // customRealm.setCachingEnabled(false); // 默认不启用Shiro 缓存
        return customRealm;
    }

    /**
     * 凭证匹配器
     *
     *
     * 神坑注意事项
     *
     * 在SpringMvc 中配置代码
     *
     * <property name ="credentialsMatcher">
     *      <bean calss = "org.apache.shiro.authc.credential.HashedCredentialsMatcher" >
     *          <property name = "HashAlgorithmName" value ="MD5">
     *               <property name = "HashIterations" value ="1024">
     *          </property>
     *      </bean>
     * </property>
     *
     *
     * 需要 bean 后面加上credentialsMatcher 这个否则 加密的字符串 和数据库中密码不一样。
     *
     * 一定要注意，这个是我踩的坑。
     *
     * @return
     */

    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于md5(md5(""));
      //  hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true); // 设置16进制的编码
        return hashedCredentialsMatcher;
    }


    /**
     *
     * 先这样写，后台不报 doFinal问题 , 后面详细研究下 rememberMe 问题。
     *
     *
     *
     * SpringMVC 配置文件写法
     *
     *  <bean id="rememberMeManager" class="org.apache.shiro.web.mgt.CookieRememberMeManager">
     *         <property name="cipherKey" value="#{T(org.apache.shiro.codec.Base64).decode('6ZmI6I2j5Y+R5aSn5ZOlAA==')}"/>
     *         <property name="cookie" ref="rememberCookie"/>     
     *  </bean
     * @return
     */


    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200000);
        cookieRememberMeManager.setCookie(simpleCookie);
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        return cookieRememberMeManager;
    }




 /*   @Bean
      public Pac4jSubjectFactory subjectFactory (){
      return new Pac4jSubjectFactory();
    }




    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();

        return filterRegistration;
    }*/



    // 权限注解(可以用在controller 和 service 方法)

}
