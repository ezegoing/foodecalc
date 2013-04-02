package com.amc.foodecalc.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.amc.foodecalc.domain.FoodPortion;

public class FoodPortionTest {

	FoodPortion fp;
	
	@Before
	public void setUp() throws Exception {
		fp = new FoodPortion();
	}

	@Test
	public void test() {
		fp.setId(1);
		fp.setFood_unit_id(1);
		fp.setName("portion of carrots");
		fp.setUser_id(1);
		fp.setWeight(200);
		
		assertEquals("portion of carrots", fp.getName());
	}

}
