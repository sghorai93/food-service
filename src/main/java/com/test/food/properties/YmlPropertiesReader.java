package com.test.food.properties;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "food")
@PropertySource(value = "classpath:interval.yml", factory = YmlReader.class)
public class YmlPropertiesReader {
	
	@Value( "${food.interval.archive:1}" )
	@Getter
	private String archive;
	
	@Getter
	@Setter
	private Map<String, Integer> interval;
	
	@Getter
	@Setter
	private Map<String, Map<String, String>> tenants;

}
