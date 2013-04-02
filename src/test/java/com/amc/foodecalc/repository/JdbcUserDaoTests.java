package com.amc.foodecalc.repository;

import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.amc.foodecalc.domain.User;
import com.amc.foodecalc.repository.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/com/mrhaki/spring/test/ContextTest-context.xml"
@ContextConfiguration(locations={"/test-context.xml"})
@Transactional
public class JdbcUserDaoTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserDao uDao;
	private int baseline = 0;
	
		
	@Before
	public void onSetUpInTransaction() throws Exception
	{
		super.deleteFromTables(new String[] {"user"});
		super.executeSqlScript("file:db/load_user_data.sql", true);
	}
	
	private void getBaseline()
	{
		List<User> uCount = uDao.getAllUsers();
		baseline = uCount.get(0).getId();		
	}
	
	@Test
	public void testGetAllUsers()
	{
		List<User> uList = uDao.getAllUsers();
		assertEquals("Anthony", uList.get(0).getUserName());
		
	}
	
	@Test
	public void testGetUser()
	{
		getBaseline();
		User u = new User();
		//u.setId(baseline+4);
		u.setUserName("John");
		
		List<User> uList = uDao.getUser(u);
		
		assertEquals("john@email.com", uList.get(0).getEmail());
	}
	
	@Test
	public void testAddUser()
	{
		User u = new User();
		u.setUserName("Timothy");
		u.setAuthority("ROLE_ADMIN");
		u.setEmail("tim@email.com");
		u.setEnabled(1);
		u.setPassword("12345");
		
		uDao.addUser(u);
		
		List<User> uList = uDao.getAllUsers();
		assertEquals("Timothy", uList.get(6).getUserName());
	}
	
	@Test
	public void testUpdateUser()
	{
		getBaseline();
		User u = new User();
		u.setId(baseline+4);
		
		//assertEquals(4, baseline);
		
		List<User> uList = uDao.getUser(u);
		u = uList.get(0);
		
		//assertEquals("test", uList.get(0).getEmail());
		
		u.setEmail("me@me.com");
		uDao.updateUser(u);
		
		uList = uDao.getAllUsers();
		assertEquals("me@me.com", uList.get(4).getEmail());
	}
	
	@Test
	public void testDeleteUser()
	{
		getBaseline();
		User u = new User();
		u.setId(baseline+4);
		
		List<User> uList = uDao.getUser(u);
		u = uList.get(0);
		uDao.deleteUser(u);
		
		uList = uDao.getAllUsers();
		assertEquals("Luke", uList.get(4).getUserName());
	}
}
