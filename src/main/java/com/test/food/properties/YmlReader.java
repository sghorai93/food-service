package com.test.food.properties;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

public class YmlReader implements PropertySourceFactory{

	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
		yamlPropertiesFactoryBean.setResources(resource.getResource());
		Properties properties = yamlPropertiesFactoryBean.getObject();
		return new PropertiesPropertySource(resource.getResource().getFilename(), properties);
	}

}
