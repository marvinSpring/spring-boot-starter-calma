package com.marvin.model;

import com.marvin.enumeration.ListenType;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "piracy.exceptionnotice")
@PropertySource(value = "classpath:application.yml")
public class PiracyExceptionNotice extends Notice{

    private boolean enabled = false;

    protected ListenType listenType = ListenType.COMMON;

    @Value("${prometheus.project-name:${spring.application.name:project}}")
    private String projectName;
}
