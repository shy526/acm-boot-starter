package com.github.qing.autoconfigure;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qing
 */
@Configuration
@ConditionalOnClass
public class AcmAutoConfiguration {
    @Bean
    @ConfigurationProperties(prefix = AcmProperties.PREFIX)
    public AcmProperties acmProperties() {
        return new AcmProperties();
    }
}
