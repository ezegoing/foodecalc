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

import com.amc.foodecalc.domain.FoodUnit;
import com.amc.foodecalc.domain.User;
import com.amc.foodecalc.repository.FoodUnitDao;

@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/com/mrhaki/spring/test/ContextTest-context.xml"
@ContextConfiguration(locations={"/test-context.xml"})
@Transactional
public class JdbcFoodUnitDaoTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private FoodUnitDao fuDao;
	private int baseline = 0;
	
		
	@Before
	public void onSetUpInTransaction() throws Exception
	{
		super.deleteFromTables(new String[] {"food_unit"});
		super.executeSqlScript("file:db/load_food_unit_data.sql", true);
	}
	
	private void getBaseline()
	{
		List<FoodUnit> fuCount = fuDao.getAllFoodUnits();
		baseline = fuCount.get(0).getId();		
	}
	
	@Test
	public void testGetFoodUnit()
	{
		getBaseline();
		FoodUnit fu = new FoodUnit();
		fu.setId(baseline);
		logger.info("baseline=" + baseline);
		
		List<FoodUnit> fuList = fuDao.getFoodUnit(fu);
		logger.info("array size=" + fuList.size());
		
		assertEquals("carrots", fuList.get(0).getName());
	}
	
	@Test
	public void testGetAllFoodUnits()
	{
		List<FoodUnit> fuList = fuDao.getAllFoodUnits();
		assertEquals("celery", fuList.get(3).getName());
	}
	
	@Test
	public void testAddFoodUnit()
	{
		FoodUnit fu = new FoodUnit();
		fu.setName("corn");
		fu.setProtein(15);
		fu.setCarbs(26);
		fu.setFat(5);
		
		fuDao.addFoodUnit(fu);
		
		List<FoodUnit> fuList = fuDao.getAllFoodUnits();
		assertEquals("corn", fuList.get(6).getName());
	}
	
	@Test
	public void testUpdateFoodUnit()
	{
		getBaseline();
		FoodUnit fu = new FoodUnit();
		fu.setId(baseline);
		fu.setName("turnip");
		fu.setProtein(15);
		fu.setCarbs(20);
		fu.setFat(2);
		
		fuDao.updateFoodUnit(fu);
		
		List<FoodUnit> fuList = fuDao.getAllFoodUnits();
		assertEquals("turnip", fuList.get(0).getName());
	}
	
	@Test
	public void testDeleteUsersFoodUnit()
	{
		User u = new User();
		u.setId(1);
		fuDao.deleteUsersFoodUnit(u);
		
		List<FoodUnit> fuList = fuDao.getAllFoodUnits();
		assertEquals("celery", fuList.get(0).getName());
	}
	
	@Test
	public void testDeleteFoodUnit()
	{
		getBaseline();
		FoodUnit fu = new FoodUnit();
		fu.setId(baseline);
		fuDao.deleteFoodUnit(fu);
		
		List<FoodUnit> fuList = fuDao.getAllFoodUnits();
		assertEquals("peas", fuList.get(0).getName());
	}
}
