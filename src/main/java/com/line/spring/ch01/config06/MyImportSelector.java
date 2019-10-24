package com.line.spring.ch01.config06;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @desc 自定义逻辑返回需要导入的组件
 * @Author xw
 * @Date 2019/10/24
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.line.spring.ch01.config06.Blue", "com.line.spring.ch01.config06.Yellow"};
    }
}
