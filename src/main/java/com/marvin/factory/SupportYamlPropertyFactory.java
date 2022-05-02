package com.marvin.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 啊，我佛了，SpringBoot都支持yml文件了但是这个@PropertySource不支持，，，还得自己实现
 * 本类是为了让这个注解支持读取yaml文件
 */
public class SupportYamlPropertyFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(resource.getResource());
        factoryBean.afterPropertiesSet();
        Properties properties = factoryBean.getObject();
        String propertiesName = name != null ? name : resource.getResource().getFilename();
        assert propertiesName != null;
        assert properties != null;
        return new PropertiesPropertySource(propertiesName, properties);
    }
}
