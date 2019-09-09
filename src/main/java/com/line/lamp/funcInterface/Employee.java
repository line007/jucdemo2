package com.line.lamp.funcInterface;

/**
 * @desc 员工信息
 * @Author xw
 * @Date 2019/9/9
 */
public class Employee {
    private String name;
    private int age;
    private int salary;

    public Employee() {
    }

    public Employee(int age) {
        this.age = age;
    }

    public Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
