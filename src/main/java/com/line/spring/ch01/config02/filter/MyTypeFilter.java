package com.line.spring.ch01.config02.filter;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @desc 自定义Filter
 * @Author xw
 * @Date 2019/8/16
 */
public class MyTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类注释信息
        //AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类资源（类路径）
        //Resource resource = metadataReader.getResource();
        // 定义过滤条件
        String className = classMetadata.getClassName();
        if (className.contains("dao")) {
            return true;
        }
        return false;
    }
}
