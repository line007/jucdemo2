package com.line.spring.ch01;

import com.line.spring.ch01.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc 配置类==配置文件
 *      @Configuration 使用配置类
 * @Author xw
 * @Date 2019/10/24
 */
@Configuration // 告诉Spring这是一个配置文件
public class MainConfig01 {

    @Bean // @Bean(name = "person")，bean对应的id默认是方法名：person
    public Person person(){
        return new Person("zhangsan", 20);
    }

    @Bean(name = "person01")
    public Person person01(){
        return new Person("zhangsan01", 20);
    }
}
