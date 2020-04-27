package com.klayiu.bootdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 刘凯
 * @create 2020-04-21 11:27
 *
 * 实现自定义注解
 */

@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

/*    String value() default "";

    String[] entry() default {};*/
}
