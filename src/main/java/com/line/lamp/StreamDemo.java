package com.line.lamp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @desc StreamDemo
 * @Author xw
 * @Date 2019/8/27
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("123", "1234", "12345", "123456", "1234567", "122222223", "123", "1234", "2422");
        Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);

        List<Student> list2 = Arrays.asList(new Student("张三"), new Student("张三"), new Student("李四"));
        Map<String, List<Student>> collect2 = list2.stream().collect(Collectors.groupingBy(Student::getName, Collectors.toList()));
        System.out.println(collect2);
        // allList(10)
        // errorList(4)
        // elseList(6)

        String[] str1 = new String[] {"123", "1234", "12345", "123456", "1234567", "122222223", "123", "1234", "2422"};
        String[] str2 = new String[] {"123", "1234", "12345"};
        List<String> allList = Arrays.stream(str1).collect(Collectors.toList());
        List<String> errorList =  Arrays.stream(str2).collect(Collectors.toList());
        allList.removeAll(errorList);
        System.out.println(allList);

        Student[] students1 = new Student[] {new Student("张三"), new Student("张三"), new Student("李四")};
        Student[] students2 = new Student[] {new Student("张三")};
        List<Student> studentAllList = Arrays.stream(students1).collect(Collectors.toList());
        List<Student> studentErrorList = Arrays.stream(students2).collect(Collectors.toList());
//        System.out.println(studentAllList.stream().filter(m -> !studentErrorList.contains(m)).distinct().collect(Collectors.toList()));
        studentAllList.removeAll(studentErrorList);
        System.out.println(studentAllList);

        String deliveryLimit = "2h/3h"; // 2H,2h -> 2/3 -> 2H/3H
        Integer startHour = getHour(deliveryLimit, 0);
        Integer endHour = getHour(deliveryLimit, 1);
        System.out.println(startHour + "--" + endHour);
    }

    public static Integer getHour(String deliveryLimit, int index) {
        return Optional.ofNullable(deliveryLimit).filter(m -> m.indexOf("/")!= -1 && m.split("/").length == 2)
                .map(m -> m.trim())
                .map(m -> m.split("/")[index])
                .map(m -> Integer.valueOf(m.substring(0, m.indexOf("H")))).orElse(0);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Student)obj).name);
    }
}
