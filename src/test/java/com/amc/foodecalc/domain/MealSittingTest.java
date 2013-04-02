package com.amc.foodecalc.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.amc.foodecalc.domain.MealSitting;

public class MealSittingTest {

	MealSitting ms;
	
	@Before
	public void setUp() throws Exception {
		ms = new MealSitting();
	}

	@Test
	public void test() {
		ms.setId(1);
		ms.setUser_id(34);
		ms.setMeal_id(2567);
		ms.setDate_and_time("28/05/2007");
		
		assertEquals(2567, ms.getMeal_id());
	}

}
