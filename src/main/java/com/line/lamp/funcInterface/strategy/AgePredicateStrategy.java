package com.line.lamp.funcInterface.strategy;

import com.line.lamp.funcInterface.Employee;
import com.line.lamp.funcInterface.MyPredicate;

/**
 * @desc AgePredicateStrategy
 * @Author xw
 * @Date 2019/9/9
 */
public class AgePredicateStrategy implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > 25;
    }
}
