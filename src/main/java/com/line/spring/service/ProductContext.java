package com.line.spring.service;

import com.line.spring.bean.Product;

/**
 * @desc ProductContext
 * @Author xw
 * @Date 2019/8/16
 */
public class ProductContext {

    // 定一个当前电梯状态
    private ProductState productState;

    public ProductContext(ProductState productState) {
        this.productState = productState;
        this.productState.setContext(this);
    }

    public void setProductState(ProductState productState) {
        this.productState = productState;
        this.productState.setContext(this);
    }

    public void updateProductStatus(Product product) { this.productState.updateProductStatus(product); }
    public void updateProductAuditStatus(Product product) {this.productState.updateProductAuditStatus(product);}

}
