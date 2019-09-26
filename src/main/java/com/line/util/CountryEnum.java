package com.line.util;

/**
 * @desc CountryEnum
 * @Author xw
 * @Date 2019/8/29
 */
public enum CountryEnum {
    OWE(1, "齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"韩"),FIVE(5,"赵"),SIX(6,"魏");

    private Integer retCode;
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum foreach_CountryEnum(int index) {
        CountryEnum[] arr = CountryEnum.values();
        for (CountryEnum element : arr) {
            if (index == element.getRetCode()) {
                return element;
            }
        }
        return null;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }
}
