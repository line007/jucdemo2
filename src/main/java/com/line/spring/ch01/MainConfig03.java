package com.line.spring.ch01;

import com.line.spring.ch01.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @desc Scope作用域
 *
 * @Author xw
 * @Date 2019/10/24
 */
@Configuration
public class MainConfig03 {

    /**
     * @Scope
     *      prototype：多例
     *      singleton：单例（spring默认值）
     *      request：同一次请求创建一个实例
     *      session：同一个session创建一个请求
     * @return
     */
    @Scope(scopeName = "prototype")
//    @Scope
    @Bean(name = "person03")
    public Person person(){
        System.out.println("给容器添加Person...");
        return new Person("zhangsan03", 20);
    }

}
