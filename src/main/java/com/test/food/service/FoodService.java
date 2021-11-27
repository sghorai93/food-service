package com.test.food.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FoodService.class);
	
	public void placeOrder() {
		LOGGER.info("Order has been Placed.....");
	}
}
