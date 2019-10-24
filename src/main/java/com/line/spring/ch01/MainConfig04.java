package com.line.spring.ch01;

import com.line.spring.ch01.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @desc Lazy懒加载
 *
 * @Author xw
 * @Date 2019/10/24
 */
@Configuration
public class MainConfig04 {

    /**
     * @Lazy
     *      单实例Bean，默认在容器启动的时候创建对象，
     *      懒加载，容器启动不创建对象，第一次使用（获取）bean创建对象并初始化。
     * @return
     */
//    @Scope(scopeName = "singleton")
    @Lazy
    @Bean(name = "person04")
    public Person person(){
        System.out.println("给容器添加person04...懒加载...");
        return new Person("zhangsan04", 20);
    }

}
