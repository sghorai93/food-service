package com.test.food.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "food")
@PropertySource("classpath:database-${envTarget:dev}.properties")
public class DataBaseProperties {
	
	@Getter
	@Setter
	private Map<String, String> datasource;
	
	/*
	 * Set the target environment like this, it will pickup that property only
	 * -DenvTarget=prod
	 */
	
}
