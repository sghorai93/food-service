package com.test.food.human;

import org.springframework.stereotype.Component;

@Component
public class HighSchool implements School {
	
	/*
	 * Component Name is not mandatory to use Qualifier.
	 * You need to specify the name in Qualifier like -
	 * @Qualifier("highSchool")
	 */

	@Override
	public String typeOfSchool() {
		return "High School";
	}

}
