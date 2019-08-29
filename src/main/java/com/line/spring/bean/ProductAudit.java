package com.line.spring.bean;

/**
 * @desc 产品审核参数
 * @Author xw
 * @Date 2019/8/16
 */
public class ProductAudit extends Product {

    private String auditStatus; // 审核结果
    private String subStatus; // 子结果
    private String principal;// 负责人
    private String remark; // 备注

    public ProductAudit(String code, String name) {
        super(code, name);
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "ProductAudit{" +
                "auditStatus='" + auditStatus + '\'' +
                ", subStatus='" + subStatus + '\'' +
                ", principal='" + principal + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
