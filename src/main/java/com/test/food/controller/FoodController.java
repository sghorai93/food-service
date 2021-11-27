package com.test.food.controller;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.food.dto.Food;
import com.test.food.human.School;
import com.test.food.properties.DataBaseProperties;
import com.test.food.properties.PropertiesReader;
import com.test.food.properties.YmlPropertiesReader;
import com.test.food.service.FoodService;

@RestController
@RequestMapping("/food-order")
public class FoodController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

	private static final String TENANT_NAME = "tenant-name";


	@Autowired
	private FoodService foodService;

	@Autowired
	private PropertiesReader propertiesReader;

	@Autowired
	private YmlPropertiesReader ymlPropertiesReader;

	@Autowired
	private DataBaseProperties dataBaseProperties;

	@Autowired
	@Qualifier("highSchool")
	private School school;

	@GetMapping(value = "/v1/place/order")
	public String placeOrder() {
		LOGGER.info("Incoming request for /v1/place/order");
		foodService.placeOrder();
		return "SUCCESS";
	}

	@GetMapping(value = "/v1/get/env")
	public void getEnvironment() {
		LOGGER.info("Incoming request for /v1/get/env");
		LOGGER.info("DB Url -> {}",propertiesReader.getUrl());
		LOGGER.info("Archive Interval -> {}",ymlPropertiesReader.getArchive());
		LOGGER.info("Interval Map -> {}",ymlPropertiesReader.getInterval());
		LOGGER.info("Datasource Map -> {}",dataBaseProperties.getDatasource());
		LOGGER.info("School Type -> {}",school.typeOfSchool());

	}


	@GetMapping(value = "/v1/mapping")
	public ResponseEntity<Food> getMapping(@RequestHeader Map<String, String> headers, @RequestBody Food food) {

		LOGGER.info("Incoming request for /v1/mapping");
		String tenantName = headers.get(TENANT_NAME);
		Validate.notBlank(tenantName, TENANT_NAME+" can't be null / empty");
		if(ymlPropertiesReader.getTenants().containsKey(tenantName)) {
			LOGGER.info("Food Name - > {}", food.getFoodName());
			LOGGER.info("Food Cuisine - > {}", food.getCuisine());
			LOGGER.info("Food Price - > {}", food.getPrice());
			food.setPrice(food.getPrice()*2);
			return new ResponseEntity<>(food, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN); 
		}
	}


}
