package com.line.spring.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @desc WindowCondition
 * @Author xw
 * @Date 2019/8/16
 */
public class WindowCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = conditionContext.getEnvironment().getProperty("os.name");
        if (property.contains("Window")) {
            return true;
        }
        return false;
    }
}
