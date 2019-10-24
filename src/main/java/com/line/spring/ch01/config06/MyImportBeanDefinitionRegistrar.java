package com.line.spring.ch01.config06;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @desc 自定义逻辑注册需要的组件
 * @Author xw
 * @Date 2019/10/24
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        // 要用全名称
//        boolean existsRed = registry.containsBeanDefinition("red");// com.line.spring.ch01.config06.Red
        boolean existsRed = registry.containsBeanDefinition("com.line.spring.ch01.config06.Red");
//        boolean existsBlue = registry.containsBeanDefinition("blue"); // com.line.spring.ch01.config06.Blue
        boolean existsBlue = registry.containsBeanDefinition("com.line.spring.ch01.config06.Blue");
        if (existsRed & existsBlue) {
            // 彩虹
            registry.registerBeanDefinition("rainbow", new RootBeanDefinition(Rainbow.class));
        }
    }
}
