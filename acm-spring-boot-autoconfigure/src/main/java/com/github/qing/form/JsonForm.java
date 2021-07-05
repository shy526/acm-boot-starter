package com.github.qing.form;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author qing
 */
public class JsonForm implements FormProcessor {
    @Override
    public Map form(String config) {
        return JSON.parseObject(config);
    }
}
