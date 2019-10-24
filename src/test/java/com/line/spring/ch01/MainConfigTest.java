package com.line.spring.ch01;

import com.line.spring.ch01.bean.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @desc
 * @Author xw
 * @Date 2019/10/24
 */
public class MainConfigTest {

    // 1.测试获取第一个bean
    @Test
    public void test_Bean() {
        System.out.println("测试获取第一个bean...");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig01.class);
        // 根据类型获取bean
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }
        // 根据id获取bean
        Person person = (Person) applicationContext.getBean("person");
        Person person01 = applicationContext.getBean("person01", Person.class);
        assertEquals("zhangsan", person.getName());
        assertEquals("zhangsan01", person01.getName());
    }

    // 2.测试@ComponentScan注解
    @Test
    public void test_ComponentScan(){
        System.out.println("测试@ComponentScan注解...");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig02.class);
        // 获取所有bean定义信息
        printBeans(applicationContext);
    }

    // 3.测试@Scope：作用域
    @Test
    public void test_Scope(){
        System.out.println("测试@Scope：作用域...");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig03.class);
        Person person1 = applicationContext.getBean("person03", Person.class);
        Person person2 = applicationContext.getBean("person03", Person.class);
        assertTrue("scope不是为singleton",person1 == person2);
    }

    // 4.测试@Lazy：懒加载
    @Test
    public void test_Lazy(){
        System.out.println("测试@Lazy：懒加载...");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig04.class);
        System.out.println("spring加载完成...");
        applicationContext.getBean("person04", Person.class);
    }

    // 5.测试@Conditional：条件
    @Test
    public void test_Conditional(){
        System.out.println("测试@Conditional：条件...");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig05.class);
        // 根据类型获取bean
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }
    }

    // 6.测试@Import：导入组件
    @Test
    public void test_Import(){
        System.out.println("测试@Import：导入组件...");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig06.class);
        // 获取所有bean定义信息
        printBeans(applicationContext);
    }

    // 打印所有bean定义信息
    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

}
