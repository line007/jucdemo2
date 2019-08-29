package com.line.spring.service;

import com.line.spring.bean.Product;
import org.springframework.stereotype.Service;

/**
 * @desc 询价采样
 * @Author xw
 * @Date 2019/8/16
 */
@Service
public class ProductInquiryOnSampleState extends ProductState {
    @Override
    public void updateProductStatus(Product product) {
        super.context.setProductState(this);
        System.out.println(String.format("更新产品[%s]，状态为：[%s]",product,"询价采样"));
    }

    @Override
    public void updateProductAuditStatus(Product product) {
        super.context.setProductState(this);
        System.out.println(String.format("更新产品[%s]，审核状态为：[%s]",product,"询价采样"));
    }
}
