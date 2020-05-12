package com.klayiu.bootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author klayiu
 * @create 2020-04-11 20:12
 *
 * swagger 配置类
 *
 * swagger 访问地址：http://localhost:8086/swagger-ui.html
 *
 * 支持restful 风格
 * 1 新增Post
 * 2 修改 put
 * 3 查询 get
 * 4 删除 delete
 *
 *
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * swagger2 配置文件
     * @return
     */

    @Bean
    Docket docket(){
        return new Docket(DocumentationType.SPRING_WEB)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.klayiu.bootdemo.controller")) // 扫描包路径
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder() // 可直接调用下面的ApiInfo信息
                        .description("接口文档描述信息") // 描述
                        .title("Spingboot集成平台搭建") // 页面标题
                        .contact(new Contact("klayiu","www.klayiu.com","dakai128865@163.com")) // 创建人
                        .version("v1.0") // 版本号
                        //   .license("apache2.0")
                        .build()
                );
    }


    /**
     * 构建ApiInfo 信息
     *
     * 两种写法 (其一)
     * @return
     */
/*    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                .title("标题")
                .description("描述")
                .termsOfServiceUrl("url")
                .contact(new Contact("","",""))
                .version("版本")
                .build();
    }*/
}
