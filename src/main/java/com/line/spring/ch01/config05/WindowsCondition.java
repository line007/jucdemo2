package com.line.spring.ch01.config05;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @desc WindowsCondition
 * @Author xw
 * @Date 2019/8/16
 */
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = conditionContext.getEnvironment().getProperty("os.name");
        if (property.contains("Window")) {
            return true;
        }
        return false;
    }
}
