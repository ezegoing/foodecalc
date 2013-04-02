package com.amc.foodecalc.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.amc.foodecalc.domain.MealPart;

public class MealPartTest {

	MealPart mp;
	
	@Before
	public void setUp() throws Exception {
		mp = new MealPart();
	}

	@Test
	public void test() {
		mp.setId(1);
		mp.setMeal_id(2);
		mp.setFood_portion_id(3);
		
		assertEquals(3, mp.getFood_portion_id());
	}

}
