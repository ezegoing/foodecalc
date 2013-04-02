package com.amc.foodecalc.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.amc.foodecalc.domain.User;

public class UserTest {

	User u;
	
	@Before
	public void setUp() throws Exception {
		u = new User();
	}

	@Test
	public void test() {
		u.setId(1);
		u.setUserName("Anthony");
		u.setEmail("ezegoing@hotmail.com");
		u.setPassword("testing");
		u.setAuthority("ROLE_USER");
		u.setEnabled(1);
		
		assertEquals("Anthony", u.getUserName());
	}

}
