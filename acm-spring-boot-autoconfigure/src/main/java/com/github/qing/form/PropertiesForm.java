package com.github.qing.form;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author qing
 */

public class PropertiesForm implements FormProcessor {

    @Override
    public Map form(String config) {
        Properties configProperties = new Properties();
        try {
            configProperties.load(new ByteArrayInputStream(config.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configProperties;
    }
}
