package com.test.food.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Configuration
@PropertySource("classpath:db.properties")
public class PropertiesReader {
	
	/*
	 * However, by default, @PropertySource doesn't load YAML files.
	 * It support only .properties file
	 */
	
	@Value( "${food.dummy.url:testUrl}" )
	@Getter
	private String url;
	
	
	/*
	 * org.springframework.beans.factory.BeanCreationException: Error creating 
	 * bean with name 'propertiesReader': Injection of autowired dependencies 
	 * failed; nested exception is java.lang.IllegalArgumentException: 
	 * Could not resolve placeholder 'food.dummy.propertyNotFound' in 
	 * value "${food.dummy.propertyNotFound}"
	 * 
	 * DON'T UNCOMMENT THIS LINE. This property is not available in properties
	 * file. THIS CODE BLOCK IS TO TEST BeanCreationException.
	 */
	
	//@Value( "${food.dummy.propertyNotFound}" )
	//@Getter
	//private String propertyNotFound;
	
	
}
