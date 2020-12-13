package com.marvin.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.marvin.properties.PiracyExceptionProperties;

@Configuration
@EnableConfigurationProperties(PiracyExceptionProperties.class)
public class PiracyConfig {

}
