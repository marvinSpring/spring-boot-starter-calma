package com.marvin.model.loader;

import com.marvin.enumeration.ListenType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@ConfigurationProperties(prefix = "calma.exceptionnotice")
@PropertySource(value = "classpath:application.yml")
public class CalmaExceptionLoader extends AbstractExceptionLoader{

    /**
     * 是否开启
     */
    private boolean enabled = false;

    /**
     * 监听类型
     */
    protected ListenType listenType = ListenType.COMMON;

    /**
     * 项目名称
     */
    @Value("${calma.exceptionnotice.exproject-name:${spring.application.name:project}}")
    private String projectName;
}
