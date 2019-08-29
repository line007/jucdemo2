package com.line.spring.state;

/**
 * @desc TODO
 * @Author xw
 * @Date 2019/8/16
 */
public enum ProductStateEnum {
    /**
     * 账号密码登录
     */
    INFO_GATHERSTATE("1", "productInfoGatherState"),
    INQUIRY_ON_SAMPLESTATE("2", "productInquiryOnSampleState");

    ProductStateEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueByCode(String code) {
        for (ProductStateEnum e : ProductStateEnum.values()) {
            if (e.getCode().equals(code))
                return e.getValue();
        }
        return null;
    }

    /**
     * 类型
     */
    private String code;
    /**
     * 描述
     */
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
