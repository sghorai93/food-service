package com.test.food.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.test.food.human.School;
import com.test.food.properties.DataBaseProperties;
import com.test.food.properties.PropertiesReader;
import com.test.food.properties.YmlPropertiesReader;
import com.test.food.service.FoodService;

@SpringBootTest
@ActiveProfiles("test")
public class FoodControllerTest {

	@MockBean
	private FoodService foodService;

	@MockBean
	private PropertiesReader propertiesReader;

	@MockBean
	private YmlPropertiesReader ymlPropertiesReader;

	@MockBean
	private DataBaseProperties dataBaseProperties;

	@MockBean
	private School school;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	void placeOrderTest() throws Exception {
		mockMvc.perform(get("/food-order/v1/place/order")).andExpect(status().isOk());
	}

	@Test
	void getEnvironmentTest() throws Exception {
		mockMvc.perform(get("/food-order/v1/get/env")).andExpect(status().isOk());
	}

	@Test
	void getMappingTestWhen200() throws Exception {
		
		Map<String, Map<String, String>> mocktenants = new HashMap<>();
		Map<String, String> tenant = new HashMap<>();
		tenant.put("tenantId", "TENANT1");
		mocktenants.put("TENANT1", tenant);
		when(ymlPropertiesReader.getTenants()).thenReturn(mocktenants);
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/food-order/v1/mapping")
				.header("tenant-name", "TENANT1");
		mockMvc.perform(mockHttpServletRequestBuilder
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"foodName\": \"Mixed Fried Rice\", \"cuisine\": \"Chinese\",  \"price\": 200.00}"));
	}
	
	@Test
	void getMappingTestWhen403() throws Exception {
		
		Map<String, Map<String, String>> mocktenants = new HashMap<>();
		Map<String, String> tenant = new HashMap<>();
		tenant.put("tenantId", "TENANT1");
		mocktenants.put("TENANT1", tenant);
		when(ymlPropertiesReader.getTenants()).thenReturn(mocktenants);
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/food-order/v1/mapping")
				.header("tenant-name", "TENANT2");
		mockMvc.perform(mockHttpServletRequestBuilder
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"foodName\": \"Mixed Fried Rice\", \"cuisine\": \"Chinese\",  \"price\": 200.00}"));
	}

}
