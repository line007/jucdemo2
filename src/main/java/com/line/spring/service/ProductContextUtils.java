package com.line.spring.service;


import com.line.spring.bean.Product;
import com.line.spring.config.MainConfig;
import com.line.spring.state.ProductStateEnum;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @desc TODO
 * @Author xw
 * @Date 2019/8/16
 */
public class ProductContextUtils {
    static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

    public static void updateProductStatus(ProductState productState, Product product) {
        new ProductContext(productState).updateProductStatus(product);
    }

    public static void updateProductStatus(ProductStateEnum productStateEnum, Product product) {
        ProductState productState = applicationContext.getBean(productStateEnum.getValue(), ProductState.class);
        new ProductContext(productState).updateProductStatus(product);
    }
}
