package com.line.lamp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @desc Java8Tester
 * @Author xw
 * @Date 2019/8/12
 */
public class Java8Tester {

    public static void main(String[] args) {
        Java8Tester tester = new Java8Tester();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) ->  { return a*b; };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);
        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

        System.out.println("=======================================");
        List<SysDict> list = new ArrayList<>();
        list.add(new SysDict(1,"test1","1",1));
        list.add(new SysDict(3,"test3","3",3));
        list.add(new SysDict(2,"test2","11",2));
        list.add(new SysDict(4,"test4","4",4));
        list.add(new SysDict(5,"test5","5",5));
        Collections.sort(list, Comparator.comparingInt(SysDict::getSort).reversed());
        list.forEach(System.out::println);
        List<SysDict> list2 = new ArrayList<>();
        Integer value = Optional.ofNullable(list).filter(m -> m.size()>=1)
                .map(m -> m.stream().sorted(Comparator.comparingInt(SysDict::getSort).reversed()).collect(Collectors.toList()).get(0))
                .map(m -> Integer.valueOf(m.getValue())+1)
                .orElse(1);
        // Optional.ofNullable(list).filter(m -> m.size()>=1)
        int value2;
        if (list != null && list.size() >= 1) {
            Collections.sort(list, Comparator.comparingInt(SysDict::getSort).reversed());
            value2 = Integer.valueOf(list.get(0).getValue()) + 1;
        } else {
            value2 = 1;
        }
        System.out.println("value=" + value);
        System.out.println("value2=" + value2);

    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

}

class SysDict {
    private Integer id;
    private String name;
    private String value;
    private Integer sort;

    public SysDict(Integer id, String name, String value, Integer sort) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SysDict{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", sort=" + sort +
                '}';
    }
}
