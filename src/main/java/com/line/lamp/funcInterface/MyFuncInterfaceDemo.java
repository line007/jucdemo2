package com.line.lamp.funcInterface;

import com.line.lamp.funcInterface.strategy.AgePredicateStrategy;
import com.line.lamp.funcInterface.strategy.SalaryPredicateStrategy;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @desc
 * 查询员工年龄大于25，薪资大于5000的
 * 一、接口引用
 * 1.常规操作
 * 2.使用策略模式
 * 3.使用匿名内部类
 * 4.使用Lambda
 * 5.使用Stream
 *
 * 二、方法引用
 * 1.对象::实例方法
 * 2.类::静态方法名
 * 3.类::实例方法名
 *
 * 三、构造器引用
 *
 * 四、数组引用
 *
 * @Author xw
 * @Date 2019/9/9
 */
public class MyFuncInterfaceDemo {
    public static void main(String[] args) {

        // 一、接口引用
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
        test_strategy(list);
        // 3.使用内部类
        test_inner_class(list);
        // 4.使用Lambda
        test_lambda(list);
        // 5.使用Stream
        test_stream01(list);
        test_stream02(list);

        // 二、方法引用
        // 1.对象::实例方法
         test_instance_method();
        // 2.类::静态方法名
         test_static_method();
        // 3.类::实例方法名（第一个参数x与第二个参数y，做一个运算判断）
         test_normal_method();

        // 三、构造器引用（实例名::new）
         test_constructor_method();

        // 四、数组引用
        test_array_method();
    }

    private static void test_array_method() {
        Function<Integer, String[]> funcArray = (x) -> new String[x];
        String[] strs = funcArray.apply(10);
        System.out.println(strs.length);
        Function<Integer, String[]> funcArray2 = String[]::new;
        String[] strs2 = funcArray.apply(20);
        System.out.println(strs2.length);
    }

    private static void test_constructor_method() {
        Supplier<Employee> funcEmp = () -> new Employee();
        System.out.println(funcEmp.get());
        Supplier<Employee> funcEmp2 = Employee::new;
        System.out.println(funcEmp2.get());
        // 指定调用构造方法
        Function<Integer, Employee> funcEmpAge = (x) -> new Employee(x);
        System.out.println(funcEmpAge.apply(101));
        Function<Integer, Employee> funcEmpAge2 = Employee::new;
        System.out.println(funcEmpAge2.apply(102));
    }

    private static void test_normal_method() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        System.out.println(bp.test("a", "b"));
    }

    private static void test_static_method() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println(com.compare(1, 2));
        Comparator<Integer> com2 = Integer::compareTo;
        System.out.println(com2.compare(1, 2));
    }

    private static void test_instance_method() {
        PrintStream ps1 = System.out;
        Consumer<String> str1 = (x) -> ps1.println(x);
        str1.accept("abc1");
        PrintStream ps2 = System.out;
        Consumer<String> str2 = ps2::println;
        str2.accept("abc2");
        Consumer<String> str3 = System.out::println;
        str3.accept("abc3");

        Employee emp = new Employee();
        Supplier<String> funcName = () -> emp.getName();
        System.out.println(funcName.get());
        Supplier<Integer> funcAge = emp::getAge;
        System.out.println(funcAge.get());
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
