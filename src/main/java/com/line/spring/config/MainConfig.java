package com.line.spring.config;

import com.line.spring.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @desc MainConfig
 * @Author xw
 * @Date 2019/8/16
 */
// 配置类==配置文件
@Configuration // 告诉Spring这是一个配置文件
@ComponentScan(value = "com.line.spring",
       /* excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class}),
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class})},*/
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
        },useDefaultFilters = false
            )
// @ComponentScan value:指定要扫描的包
// excludeFilters=扫描时排除哪些组件
// includeFilters=扫描时只包含哪些组件
public class MainConfig {

    // 给容器中注册一个Bean；类型为返回值的类型，id默认是方法名作为id
    @Bean("person")
    public Person person01() {
        return new Person("lisi", 20);
    }

}
