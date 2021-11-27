package com.test.food.human;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class JuniorSchool implements School {
	
	/*
	 * Component Name is not mandatory to use Qualifier.
	 * You need to specify the name in Qualifier like -
	 * @Qualifier("juniorSchool")
	 */
	
	/*
	 * If Qualifier tag is missing then only it looks for Primary tag
	 */
	
	@Override
	public String typeOfSchool() {
		return "Junior School";
	}

}
