package com.line.lamp.funcInterface.strategy;

import com.line.lamp.funcInterface.Employee;
import com.line.lamp.funcInterface.MyPredicate;

/**
 * @desc SalaryPredicateStrategy
 * @Author xw
 * @Date 2019/9/9
 */
public class SalaryPredicateStrategy implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 5000;
    }
}
