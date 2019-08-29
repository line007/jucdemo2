package com.line.spring.config;

import com.line.spring.bean.Person;
import com.line.spring.condition.LinuxCondition;
import com.line.spring.condition.WindowCondition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * @desc TODO
 * @Author xw
 * @Date 2019/8/16
 */
// 配置类==配置文件
@Configuration // 告诉Spring这是一个配置文件
@ComponentScan(value = "com.line.spring")
@Conditional(LinuxCondition.class)
public class MainConfig2 {

    // 给容器中注册一个Bean；类型为返回值的类型，
    @Bean
    public Person person() {
        return new Person("lisi", 20);
    }

    @Conditional(WindowCondition.class)
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linus")
    public Person person02() {
        return new Person("Linus", 43);
    }
}
