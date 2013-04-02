package com.amc.foodecalc.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.amc.foodecalc.domain.Meal;

public class MealTest {

	Meal m;
	
	@Before
	public void setUp() throws Exception {
		m = new Meal();
	}

	@Test
	public void test() {
		m.setId(1);
		m.setUser_id(2);
		m.setName("My Dinner");
		
		assertEquals("My Dinner", m.getName());
	}

}
