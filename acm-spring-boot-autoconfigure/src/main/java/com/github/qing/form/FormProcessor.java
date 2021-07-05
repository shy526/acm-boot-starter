package com.github.qing.form;

import java.util.Map;

/**
 * @author qing
 */
public interface FormProcessor {
    /**
     * 将指定str 解析成map
     *
     * @param config config
     * @return Map<String, String>
     */
    Map form(String config);
}
