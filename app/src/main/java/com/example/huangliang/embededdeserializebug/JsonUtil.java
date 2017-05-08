package com.example.huangliang.embededdeserializebug;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * Created by huangliang on 17/5/8.
 */

public class JsonUtil {
    public static String pojo2json(Object pojo) {
        return JSON.toJSONString(pojo);
    }

    public static <T> T json2pojo(String jsonAsString, Class<T> pojoClass) throws JSONException {
        return JSON.parseObject(jsonAsString, pojoClass);
    }
}
