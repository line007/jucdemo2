package com.line.spring.ch01;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @desc 配置类==配置文件
 * @Configuration 使用配置类
 * @ComponentScan 扫描组件包
 * @Filter
 *      1.excludeFilters：扫描时需要排除哪些组件
 *      2.includeFilters + useDefaultFilters=false：扫描时需要包含哪些组件
 *      3.FilterType
 *              FilterType.ANNOTATION 按照注释
 *              FilterType.ASSIGNABLE_TYPE 按照给定的类型
 *              FilterType.CUSTOM 按照自定义规则
 * @Author xw
 * @Date 2019/10/24
 */
@Configuration
@ComponentScan(value = "com.line.spring.ch01.config02",
        /*excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {PersonService.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
        },*/
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
        }, useDefaultFilters = false
)
public class MainConfig02 {

}
