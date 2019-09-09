package com.line.lamp.funcInterface;

import com.line.lamp.funcInterface.strategy.AgePredicateStrategy;
import com.line.lamp.funcInterface.strategy.SalaryPredicateStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @desc
 * 查询员工年龄大于25，薪资大于5000的
 * 1.常规操作
 * 2.使用策略模式
 * 3.使用匿名内部类
 * 4.使用Lambda
 * 5.使用Stream
 * @Author xw
 * @Date 2019/9/9
 */
public class MyFuncInterfaceDemo {
    public static void main(String[] args) {
        List<Employee> list = Arrays.asList(
                new Employee("zhangsan", 20, 2500),
                new Employee("lisi", 25, 4000),
                new Employee("wangwu", 35, 8000),
                new Employee("maliu", 45, 9000),
                new Employee("tianqi", 50, 8000),
                new Employee("smis", 26, 3000));
        // 1.常规操作
        test_normal(list);
        // 2.使用策略格式
        //test_strategy(list);
        // 3.使用内部类
        //test_inner_class(list);
        // 4.使用Lambda
        //test_lambda(list);
        // 5.使用Stream
        //test_stream01(list);
        //test_stream02(list);
    }

    private static void test_stream02(List<Employee> list) {
        System.out.println("----------年龄大于25岁 && 薪资大于5K的-----------");
        filterEmployee(list, (e) -> e.getAge() > 25 && e.getSalary() > 5000).stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    private static void test_stream01(List<Employee> list) {
        System.out.println("----------年龄大于25岁 && 薪资大于5K的-----------");
        filterEmployee(list, (e) -> e.getAge() > 25 && e.getSalary() > 5000).stream()
                .forEach(System.out::println);
    }

    private static void test_lambda(List<Employee> list) {
        /*filterEmployee(list, (Employee e) -> {return e.getAge() > 25 && e.getSalary() > 5000;});
        filterEmployee(list, (Employee e) -> e.getAge() > 25 && e.getSalary() > 5000);*/
        List<Employee> newList = filterEmployee(list, (e) -> e.getAge() > 25 && e.getSalary() > 5000);
        System.out.println("----------年龄大于25岁 && 薪资大于5K的-----------");
        newList.stream().forEach(System.out::println);
    }

    private static void test_inner_class(List<Employee> list) {
        List<Employee> newList = filterEmployee(list, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee e) {
                return e.getAge() > 25 && e.getSalary() > 5000;
            }
        });
        System.out.println("----------年龄大于25岁 && 薪资大于5K的-----------");
        newList.stream().forEach(System.out::println);
    }

    private static void test_strategy(List<Employee> list) {
        List<Employee> newList = filterEmployee(list, new AgePredicateStrategy());
        System.out.println("----------年龄大于25岁的-----------");
        newList.stream().forEach(System.out::println);
        newList = filterEmployee(newList, new SalaryPredicateStrategy());
        System.out.println("----------薪资大于5K的-----------");
        newList.stream().forEach(System.out::println);
    }

    private static List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> myPredicate) {
        List<Employee> employees = new ArrayList<>();
        for (Employee emp : list) {
            if (myPredicate.test(emp)) { // myPredicate
                employees.add(emp);
            }
        }
        return employees;
    }

    private static void test_normal(List<Employee> list) {
        List<Employee> newList = filterEmpByAge(list);
        System.out.println("----------大于25岁的-----------");
        newList.stream().forEach(System.out::println);
        newList = filterEmpBySalary(newList);
        System.out.println("----------薪资大于5K的-----------");
        newList.stream().forEach(System.out::println);
    }

    private static List<Employee> filterEmpByAge(List<Employee> list) {
        List<Employee> employees = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() > 25) {
                employees.add(emp);
            }
        }
        return employees;
    }

    private static List<Employee> filterEmpBySalary(List<Employee> list) {
        List<Employee> employees = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getSalary() > 5000) {
                employees.add(emp);
            }
        }
        return employees;
    }
}
