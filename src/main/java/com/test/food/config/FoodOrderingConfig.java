package com.test.food.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = "com.test.food")
public class FoodOrderingConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FoodOrderingConfig.class);
	
	@Profile("dev")
	@Bean
	public void getEnvironmentDev() {
		LOGGER.info("THIS IS DEV ENV");
	}
	
	@Profile("prod")
	@Bean
	public void getEnvironmentProd() {
		LOGGER.info("THIS IS PROD ENV");
	}
	
}
