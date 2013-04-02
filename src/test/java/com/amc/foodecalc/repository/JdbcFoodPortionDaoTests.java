package com.amc.foodecalc.repository;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.amc.foodecalc.domain.FoodPortion;
import com.amc.foodecalc.domain.User;
import com.amc.foodecalc.repository.FoodPortionDao;

@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/com/mrhaki/spring/test/ContextTest-context.xml"
@ContextConfiguration(locations={"/test-context.xml"})
@Transactional
public class JdbcFoodPortionDaoTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private FoodPortionDao fpDao;
	private int baseline = 0;
	
	
	@Before
	public void onSetUpInTransaction() throws Exception
	{
		super.deleteFromTables(new String[] {"food_portion"});
		super.executeSqlScript("file:db/load_food_portion_data.sql", true);
	}
	
	@Test
	public void testGetAllFoodPortions()
	{
		List<FoodPortion> fp = fpDao.getAllFoodPortions();
		assertEquals("Wrong number of food portions?", 3, fp.size());
		assertEquals("soup", fp.get(0).getName());
	}
	
	@Test
	public void testGetUsersFoodPortions()
	{
		User u = new User();
		u.setId(2);
		List<FoodPortion> fp = fpDao.getUsersFoodPortions(u);
		assertEquals("Milk", fp.get(0).getName());
	}
		
	@Test
	public void testGetFoodPortion()
	{
		getBaseline();
		//logger.info("Id = " + fpCount.get(0).getId());
		
		FoodPortion fp = new FoodPortion();
		fp.setId(baseline);
		List<FoodPortion> fpList = fpDao.getFoodPortion(fp);
		
		
		assertEquals("soup", fpList.get(0).getName());
	}
	
	private void getBaseline()
	{
		List<FoodPortion> fpCount = fpDao.getAllFoodPortions();
		baseline = fpCount.get(0).getId();		
	}
	
	@Test
	public void testAddFoodPortion()
	{
		getBaseline();
		FoodPortion fp = new FoodPortion();
		fp.setId(baseline+3);
		fp.setUser_id(4);
		fp.setName("Twix");
		fp.setWeight(46);
		fpDao.addFoodPortion(fp);
		
		List<FoodPortion> fpFP = fpDao.getFoodPortion(fp);
		List<FoodPortion> fpList = fpDao.getAllFoodPortions();
		
		assertEquals(4, fpList.size());
		assertEquals("Twix", fpFP.get(0).getName());
	}
	
	@Test
	public void testUpdateFoodPortion()
	{
		List<FoodPortion> fpList = fpDao.getAllFoodPortions();
		FoodPortion original = fpList.get(0);
				
		original.setName("chicken soup");
		fpDao.updateFoodPortion(original);
		
		fpList = fpDao.getAllFoodPortions();
		original = fpList.get(0);
		assertEquals("chicken soup", original.getName());
	}
	
	@Test
	public void testDeleteFoodPortion()
	{
		getBaseline();
		FoodPortion fp = new FoodPortion();
		fp.setId(baseline);
		fpDao.deleteFoodPortion(fp);
		List<FoodPortion> fpList = fpDao.getAllFoodPortions();
			
		assertEquals("Milk", fpList.get(0).getName());
	}
	
	@Test
	public void testDeleteUsersFoodPortions()
	{
		getBaseline();
		User u = new User();
		u.setId(2);
		
		fpDao.deleteUsersFoodPortions(u);
		
		List<FoodPortion> fpList = fpDao.getAllFoodPortions();
		assertEquals("soup", fpList.get(0).getName());
		assertEquals("Crisps", fpList.get(1).getName());
	}
}
