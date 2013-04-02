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

import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.domain.User;
import com.amc.foodecalc.repository.MealDao;

@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/com/mrhaki/spring/test/ContextTest-context.xml"
@ContextConfiguration(locations={"/test-context.xml"})
@Transactional
public class JdbcMealDaoTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private MealDao mDao;
	private int baseline = 0;
	
	
	@Before
	public void onSetUpInTransaction() throws Exception
	{
		super.deleteFromTables(new String[] {"meal"});
		super.executeSqlScript("file:db/load_meal_data.sql", true);
	}
	
	private void getBaseline()
	{
		List<Meal> mCount = mDao.getAllMeals();
		baseline = mCount.get(0).getId();		
	}
	
	@Test
	public void testGetAllMeals()
	{
		List<Meal> mList = mDao.getAllMeals();
		assertEquals("Meat meal", mList.get(1).getName());
	}

	@Test
	public void testGetUsersMeals()
	{
		User u = new User();
		u.setId(2);
		List<Meal> mList = mDao.getUserMeals(u);
		
		assertEquals("Veggie meal", mList.get(0).getName());
			
	}
	
	@Test
	public void testGetMeal()
	{
		getBaseline();
		Meal m = new Meal();
		m.setId(baseline);
		List<Meal> mList = mDao.getMeal(m);
		
		assertEquals("Veg meal", mList.get(0).getName());
	}
	
	@Test
	public void testAddMeal()
	{
		Meal m = new Meal();
		m.setName("Choc Fudge");
		m.setUser_id(3);
		mDao.addMeal(m);
		
		List<Meal> mList = mDao.getAllMeals();
		assertEquals("Choc Fudge", mList.get(6).getName());
	}
	
	@Test
	public void testUpdateMeal()
	{
		getBaseline();
		Meal m = new Meal();
		m.setId(baseline);
		m.setName("Chick Peas");
		m.setUser_id(6);
		mDao.updateMeal(m);
		
		List<Meal> mList = mDao.getAllMeals();
		assertEquals("Chick Peas", mList.get(0).getName());
	}
	
	@Test
	public void testDeleteMeal()
	{
		getBaseline();
		Meal m = new Meal();
		m.setId(baseline);
		mDao.deleteMeal(m);
		
		List<Meal> mList = mDao.getAllMeals();
		
		assertEquals("Meat meal", mList.get(0).getName());
	}
	
	@Test
	public void testDeleteUsersMeals()
	{
		User u = new User();
		u.setId(1);
		mDao.deleteUsersMeals(u);
		
		List<Meal> mList = mDao.getAllMeals();
		assertEquals("Veggie meal", mList.get(0).getName());
	}
}
