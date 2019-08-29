package com.line.spring.service;

import com.line.spring.bean.Product;

/**
 * @desc TODO
 * @Author xw
 * @Date 2019/8/16
 */
public abstract class ProductState {
    protected ProductContext context;

    public void setContext(ProductContext context) {
        this.context = context;
    }

    public ProductState() {

    }

    public ProductState(ProductContext context) {
        this.context = context;
    }

    public void updateProductStatus(Product product) { } // 更新产品状态
    public void updateProductAuditStatus(Product product) {} // 更新产品审核状态

}
