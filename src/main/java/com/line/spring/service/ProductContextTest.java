package com.line.spring.service;

import com.line.spring.bean.Product;
import com.line.spring.bean.ProductAudit;

/**
 * @desc TODO
 * @Author xw
 * @Date 2019/8/16
 */
public class ProductContextTest {
    public static void main(String[] args) {

        ProductContextUtils.updateProductStatus(new ProductInfoGatherState(), new Product("P001", "测试产品"));
        //
        new ProductContext(new ProductInquiryOnSampleState()).updateProductStatus(new Product("P001", "测试产品"));
        ProductAudit product = new ProductAudit("P001", "测试产品");
        product.setAuditStatus("通过");
        product.setSubStatus("产品编辑");
        product.setPrincipal("张三");
        ProductContextUtils.updateProductStatus(new ProductInquiryOnSampleState(), product);
    }
}
