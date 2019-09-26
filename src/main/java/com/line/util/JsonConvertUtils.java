package com.line.util;

/**
 * @desc json转换工具类，针对第二层对象无法转换问题
 * @Author xw
 * @Date 2019/9/19
 */
public class JsonConvertUtils {

    // com.alibaba.fastjson.JSONArray

    /*public static <T> List<T> getList(List<T> data, Class<T> t) {
        return JSONArray.parseArray(JSONArray.toJSONString(data),t);
    }

    public static <T> T getFirst(List<T> data, Class<T> t) {
        return Optional.ofNullable(getList(data, t)).map(m -> m.get(0)).orElse(null);
    }*/
}
