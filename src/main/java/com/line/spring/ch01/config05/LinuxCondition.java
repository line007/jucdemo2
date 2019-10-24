package com.line.spring.ch01.config05;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @desc LinuxCondition
 * @Author xw
 * @Date 2019/8/16
 */
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = conditionContext.getEnvironment().getProperty("os.name");
        if (property.contains("linux")) {
            return true;
        }
        return false;
    }
}
