package com.line.spring.ch01;

import com.line.spring.ch01.bean.Person;
import com.line.spring.ch01.config05.LinuxCondition;
import com.line.spring.ch01.config05.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @desc Conditional条件
 * 1.是指符合条件的才使用配置
 * 2.Conditional注解可以添加在类、方法上
 *  案例：根据当前系统自动使用不同的配置类，linux使用linuxCondition，window使用windowCondition
 *
 * @Author xw
 * @Date 2019/10/24
 */
@Conditional({WindowsCondition.class})
@Configuration
public class MainConfig05 {

//    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01(){
        return new Person("Bill Gates", 62);
    }

//    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02(){
        return new Person("linus", 48);
    }

}
