package com.github.qing.autoconfigure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

/**
 * acm
 * @author qing
 */
public class AcmEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
    public static final int DEFAULT_ORDER = ConfigFileApplicationListener.DEFAULT_ORDER + 1;


    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
       MutablePropertySources propertySources = environment.getPropertySources();
        if(propertySources.contains(AcmProperties.PREFIX)) {
            return;
        }
        AcmProperties acmProperties = Binder.get(environment).bind(AcmProperties.PREFIX, Bindable.of(AcmProperties.class)).get();
        PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("acm", AcmConfig.load(acmProperties));
        environment.getPropertySources().addLast(propertiesPropertySource);
    }
}
