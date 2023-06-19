package com.marvin.model.web;

import com.marvin.util.factory.SupportYamlPropertyFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "calma.exceptionnotice")
@PropertySource(value = "classpath:application.yml",factory = SupportYamlPropertyFactory.class)
public class WebNoticeInfo {

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    private boolean enabled;

    private boolean auto;

}
