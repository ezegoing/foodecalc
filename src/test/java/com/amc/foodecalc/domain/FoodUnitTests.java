package com.amc.foodecalc.domain;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.amc.foodecalc.domain.FoodUnit;

public class FoodUnitTests {

	FoodUnit fu;
	
	@Before
	public void setUp() throws Exception {
		fu = new FoodUnit();
	}

	@Test
	public void test() {
		fu.setId(1);
		fu.setUser_id(1);
		fu.setName("potato");
		fu.setProtein(20);
		fu.setName("potato");
		fu.setCarbs(10);
		fu.setFat(5);
		
		assertEquals("potato", fu.getName());
	}

}
