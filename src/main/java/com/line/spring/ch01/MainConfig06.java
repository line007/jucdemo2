package com.line.spring.ch01;

import com.line.spring.ch01.config06.Color;
import com.line.spring.ch01.config06.MyImportBeanDefinitionRegistrar;
import com.line.spring.ch01.config06.MyImportSelector;
import com.line.spring.ch01.config06.Red;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @desc Import：导入组件
 *  1.直接导入普通类，如：Color.class, Red.class
 *  2.使用ImportSelector选择器导入，如：MyImportSelector，
 *      (AnnotationMetadata annotationMetadata) {
 *          return new String[]{"com.line.spring.ch01.config06.Blue", "com.line.spring.ch01.config06.Yellow"};
 *      }
 *  3.使用ImportBeanDefinitionRegistrar注册组件，如MyImportBeanDefinitionRegistrar
 *      (AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
 *          registry.registerBeanDefinition("rainbow", new RootBeanDefinition(Rainbow.class));
 *      }
 * @Author xw
 * @Date 2019/10/24
 */
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig06 {

}
