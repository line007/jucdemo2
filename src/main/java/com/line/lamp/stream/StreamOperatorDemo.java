package com.line.lamp.stream;

import com.line.lamp.funcInterface.Employee;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @desc Stream常规操作
 * 1.创建Stream的几种方式
 * 2.流转集合的几种方式
 * 3.统计
 *  3.1 以总数、平均值、最小值为例
 *  3.2 归约
 * 4.字符串拼接
 * 5.分组、分区
 * @Author xw
 * @Date 2019/9/11
 */
public class StreamOperatorDemo {
    static List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 40, 2500, Employee.EmployeeType.EMP),
            new Employee("lisi", 25, 4000, Employee.EmployeeType.EMP),
            new Employee("wangwu", 35, 2500, Employee.EmployeeType.EMP),
            new Employee("maliu", 45, 9000, Employee.EmployeeType.MGR),
            new Employee("tianqi", 50, 8000, Employee.EmployeeType.MGR),
            new Employee("smis", 26, 3000, Employee.EmployeeType.EMP));

    public static void main(String[] args) {
        // 1.创建Stream的几种方式
        test_create_stream();
        // 2.转集合的几种方式
        test_to_collect();
        // 3.统计（以总数、平均值、最小值为例）
        test_to_count();
        // 4.字符串拼接
        test_append_str();
        // 5.分组、分区
        test_groupby_partitioningBy();
    }

    private static void test_groupby_partitioningBy() {
        // 按员工类型分组
        Map<Employee.EmployeeType, Long> map1 = employees.stream().collect(Collectors.groupingBy(Employee::getEmployeeType, Collectors.counting()));
        System.out.println(map1);
        // 按员工类型 + 薪资分组
        Map<Employee.EmployeeType, Map<String, List<Employee>>> map2 = employees.stream().collect(Collectors.groupingBy(Employee::getEmployeeType, Collectors.groupingBy((e) -> {
            if (e.getSalary() >= 8000) {
                return "高收入";
            } else if (e.getSalary() >= 4000) {
                return "中收入";
            } else {
                return "解决温饱";
            }
        })));
        System.out.println(map2);
        // 按薪资分区，>=8000 一个区，另外一个区
        Map<Boolean, List<Employee>> map3 = employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() >= 8000));
        System.out.println(map3);
    }

    private static void test_append_str() {
        String str = employees.stream().map(Employee::getName).collect(Collectors.joining(",","(",")"));
        System.out.println("str=" + str);
    }

    private static void test_to_count() {
        System.out.println("总数：" + employees.stream().collect(Collectors.counting()));
        System.out.println("平均值：" + employees.stream().collect(Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("最小值：" + employees.stream().collect(Collectors.minBy((x, y) -> Double.compare(x.getSalary(), y.getSalary()))).get()); // 已过时
        System.out.println("最小值：" + employees.stream().min(Comparator.comparing(Employee::getSalary)).get());
        System.out.println("最小值：" + employees.stream().min(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getAge)).get());
        System.out.println("------------另一种方式-----------");
        DoubleSummaryStatistics collect = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("总数：" + collect.getCount());
        System.out.println("平均值：" + collect.getAverage());
        System.out.println("最小值：" + collect.getMin());

        // 归约
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        Integer amount1 = employees.stream().map(Employee::getSalary).reduce(0, (x, y) -> x + y);
        System.out.println(amount1);
        Integer amount2 = employees.stream().map(Employee::getSalary).reduce(Integer::sum).get();
        System.out.println(amount2);
    }

    private static void test_to_collect() {
        List<String> list = employees.stream().map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("----------------------------------------");
        Set<String> set = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("----------------------------------------");
        HashSet<String> hashSet = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    private static void test_create_stream() {
        Stream<String> stream1 = new ArrayList<String>().stream();
        Stream<String> stream2 = Arrays.stream(new String[]{"a", "b", "c"});
        Stream<String> stream3 = Stream.of("a", "b", "c");
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2); // 无限流
        stream4.limit(10).forEach(System.out::println);
        Stream.generate(() -> Math.random())
                .limit(5).forEach(System.out::println);
        System.out.println("----------------------------------------");
    }
}
