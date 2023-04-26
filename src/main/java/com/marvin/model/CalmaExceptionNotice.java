package com.marvin.model;

import com.marvin.enumeration.ListenType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

//@EqualsAndHashCode(callSuper = true)
@Data
@ConfigurationProperties(prefix = "calma.exceptionnotice")
@PropertySource(value = "classpath:application.yml")
public class CalmaExceptionNotice /*extends Notice*/{

    private boolean enabled = false;

    protected ListenType listenType = ListenType.COMMON;

    @Value("${calma.exceptionnotice.exproject-name:${spring.application.name:project}}")
    private String projectName;
}
