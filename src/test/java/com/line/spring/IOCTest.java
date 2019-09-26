package com.line.spring;

import com.line.spring.bean.Person;
import com.line.spring.bean.Product;
import com.line.spring.bean.ProductAudit;
import com.line.spring.config.MainConfig;
import com.line.spring.config.MainConfig2;
import com.line.spring.service.*;
import com.line.spring.state.ProductStateEnum;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @desc IOCTest
 * @Author xw
 * @Date 2019/8/16
 */
public class IOCTest {
    @Test
    public void test01() {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Object person = applicationContext.getBean("person");
        System.out.println(person);

        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }
    }

    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void test03() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }
    }

    @Test
    public void test04() {
        ProductContextUtils.updateProductStatus(ProductStateEnum.INFO_GATHERSTATE, new Product("P001", "测试产品"));
        ProductContextUtils.updateProductStatus(ProductStateEnum.INQUIRY_ON_SAMPLESTATE, new Product("P001", "测试产品"));
        ProductAudit product = new ProductAudit("P001", "测试产品");
        product.setAuditStatus("通过");
        product.setSubStatus("产品编辑");
        product.setPrincipal("张三");
        ProductContextUtils.updateProductStatus(ProductStateEnum.INQUIRY_ON_SAMPLESTATE, product);
    }
}
