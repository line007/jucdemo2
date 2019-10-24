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

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @desc IOCTest
 * @Author xw
 * @Date 2019/8/16
 */
public class IOCTest {

    @Test
    public void testxx() {

        String str = "2019/10/01 12:05:45";
        LocalDateTime localDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        LocalDate localDate = LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDate.getDayOfMonth());

        /*System.out.println(localDateTime.getDayOfMonth());

        BigDecimal x = new BigDecimal(10);
        BigDecimal y = new BigDecimal(6);
        System.out.println(x.divide(y, 4, RoundingMode.HALF_UP));

        System.out.println(Optional.ofNullable(null).orElse("aaa"));

        System.out.println(Arrays.asList("a","b","c").subList(0,3));

        *//*for (int i = 0; i < 10; i++) {
            if (i > 3) {
                System.out.println("bbbb");
                return;
            }
            System.out.println("aaaaaaaa");
        }
        System.out.println("cccc");*//*

        List<Integer> xx = Arrays.asList(1,3,5,7,9);
        List<Integer> xx2 = xx.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(xx2);*/

        //System.out.println(Comparator.comparingInt(1, 2));
    }

    @Test
    public void test_spring_annotation_config_bean() {

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
