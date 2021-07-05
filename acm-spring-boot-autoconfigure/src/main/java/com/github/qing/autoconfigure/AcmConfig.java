package com.github.qing.autoconfigure;

import com.alibaba.edas.acm.ConfigService;
import com.alibaba.edas.acm.exception.ConfigException;


import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Properties;

import static com.alibaba.edas.acm.ConfigService.getConfig;

import com.alibaba.edas.acm.listener.ConfigChangeListener;
import com.github.qing.form.FormFactory;
import com.github.qing.form.FormProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author qing
 */
public class AcmConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcmConfig.class);
    public static final Properties GLOBAL_PROPERTIES = new Properties();

    public static Properties load(AcmProperties acmProperties) {
        ConfigService.init(acmProperties.getAcmProperties());
        String groupId = acmProperties.getGroupId();
        FormProcessor formProcessor = FormFactory.getFormProcessor(acmProperties.getForm());
        System.out.println("------------------load------------------------");
        acmProperties.getDataIds().forEach(dataId -> {
            try {
                String config = getConfig(dataId, groupId, 300);
                if (init(config, dataId, groupId, formProcessor)) {
                    throw new ConfigException(groupId + "-" + dataId);
                }
                ConfigService.addListener(dataId, groupId, new ConfigChangeListener() {
                    @Override
                    public void receiveConfigInfo(String s) {
                        init(s, dataId, groupId, formProcessor);
                    }
                });
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
        System.out.println("------------------load------------------------");
        return GLOBAL_PROPERTIES;
    }

    private static boolean init(String config, String dataId, String groupId, FormProcessor formProcessor) {
        String str = "------------------" + groupId + "-" + dataId + "------------------------";
        System.out.println(str);
        boolean result = false;
        try {
            if (StringUtils.isEmpty(config)) {
                result = true;
                System.out.println("is empty");
            }
            Map configMap = formProcessor.form(config);
            if (configMap.size() <= 0) {
                result = true;
                System.out.println("is empty");
            }
            configMap.forEach((key, value) -> {
                GLOBAL_PROPERTIES.put(key, value);
                System.out.println(key + "=" + value);
            });

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            result = true;
        } finally {
            System.out.println(str);
        }

        return result;
    }
}
