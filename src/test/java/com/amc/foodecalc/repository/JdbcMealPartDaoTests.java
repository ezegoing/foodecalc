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
import com.amc.foodecalc.domain.MealPart;
import com.amc.foodecalc.repository.MealPartDao;

@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/com/mrhaki/spring/test/ContextTest-context.xml"
@ContextConfiguration(locations={"/test-context.xml"})
@Transactional
public class JdbcMealPartDaoTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private MealPartDao mpDao;
	private int baseline = 0;
	
		
	@Before
	public void onSetUpInTransaction() throws Exception
	{
		super.deleteFromTables(new String[] {"meal_part"});
		super.executeSqlScript("file:db/load_meal_part_data.sql", true);
	}
	
	private void getBaseline()
	{
		List<MealPart> mpCount = mpDao.getAllMealParts();
		baseline = mpCount.get(0).getId();		
	}
	
	@Test
	public void testGetAllMealParts()
	{
		List<MealPart> mpList = mpDao.getAllMealParts();
		assertEquals(2, mpList.get(1).getFood_portion_id());
	}
	
	@Test
	public void testGetUsersMealParts()
	{
		Meal m = new Meal();
		m.setId(2);
		List<MealPart> mpList = mpDao.getUsersMealParts(m);
		assertEquals(1, mpList.get(0).getFood_portion_id());
		assertEquals(5, mpList.get(1).getFood_portion_id());
		assertEquals(7, mpList.get(2).getFood_portion_id());
	}
	
	@Test
	public void testGetMealPart()
	{
		getBaseline();
		MealPart mp = new MealPart();
		mp.setId(baseline);
		List<MealPart> mpList = mpDao.getMealPart(mp);
		
		assertEquals(1, mpList.get(0).getFood_portion_id());
	}
	
	@Test
	public void testAddMealPart()
	{
		MealPart mp = new MealPart();
		mp.setMeal_id(3);
		mp.setFood_portion_id(5);
		mpDao.addMealPart(mp);
		
		List<MealPart> mpList = mpDao.getAllMealParts();
		assertEquals(5, mpList.get(6).getFood_portion_id());
	}
	
	@Test
	public void testUpdateMealPart()
	{
		getBaseline();
		MealPart mp = new MealPart();
		mp.setId(baseline);
		mp.setMeal_id(45);
		mp.setFood_portion_id(1234);
		mpDao.updateMealPart(mp);
		
		List<MealPart> mpList = mpDao.getAllMealParts();
		assertEquals(1234, mpList.get(0).getFood_portion_id());
	}
	
	@Test
	public void testDeleteMealPart()
	{
		getBaseline();
		MealPart mp = new MealPart();
		mp.setId(baseline);
		mpDao.deleteMealPart(mp);
		
		List<MealPart> mpList = mpDao.getAllMealParts();
		assertEquals(2, mpList.get(0).getFood_portion_id());
	}
	
	@Test
	public void testDeleteMeal()
	{
		Meal m = new Meal();
		m.setId(1);
		mpDao.deleteMeal(m);
		List<MealPart> mpList = mpDao.getAllMealParts();
		
		assertEquals(5, mpList.get(1).getFood_portion_id());
	}
}
