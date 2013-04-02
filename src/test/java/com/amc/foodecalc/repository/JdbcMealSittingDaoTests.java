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

import com.amc.foodecalc.domain.MealSitting;
import com.amc.foodecalc.domain.User;
import com.amc.foodecalc.repository.MealSittingDao;

@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/com/mrhaki/spring/test/ContextTest-context.xml"
@ContextConfiguration(locations={"/test-context.xml"})
@Transactional
public class JdbcMealSittingDaoTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private MealSittingDao msDao;
	private int baseline = 0;
	
		
	@Before
	public void onSetUpInTransaction() throws Exception
	{
		super.deleteFromTables(new String[] {"meal_sitting"});
		super.executeSqlScript("file:db/load_meal_sitting_data.sql", true);
	}
	
	private void getBaseline()
	{
		List<MealSitting> msCount = msDao.getAllMealSittings();
		baseline = msCount.get(0).getId();		
	}
	
	@Test
	public void testGetAllMealSittings()
	{
		List<MealSitting> msList = msDao.getAllMealSittings();
		assertEquals(2, msList.get(1).getMeal_id());
	}
	
	@Test
	public void testGetUsersMealSittings()
	{
		User u = new User();
		u.setId(2);
		List<MealSitting> msList = msDao.getUsersMealSittings(u);
		
		assertEquals(7, msList.get(1).getMeal_id());
	}
	
	@Test
	public void testGetMealSitting()
	{
		getBaseline();
		MealSitting ms = new MealSitting();
		ms.setId(baseline);
		List<MealSitting> msList = msDao.getMealSitting(ms);
		assertEquals(1, msList.get(0).getMeal_id());
	}
	
	@Test
	public void testAddMealSitting()
	{
		MealSitting ms = new MealSitting();
		ms.setUser_id(34);
		ms.setMeal_id(1456);
		msDao.addMealSitting(ms);
		
		List<MealSitting> msList = msDao.getAllMealSittings();
		assertEquals(1456, msList.get(6).getMeal_id());
	}
	
	@Test
	public void testUpdateMealSitting()
	{
		getBaseline();
		MealSitting ms = new MealSitting();
		ms.setId(baseline);
		ms.setUser_id(567);
		ms.setMeal_id(1233);
		msDao.updateMealSitting(ms);
		
		List<MealSitting> msList = msDao.getAllMealSittings();
		assertEquals(567, msList.get(0).getUser_id());
	}
	
	@Test
	public void testDeleteMealSitting()
	{
		getBaseline();
		MealSitting ms = new MealSitting();
		ms.setId(baseline);
		msDao.deleteMealSitting(ms);
		
		List<MealSitting> msList = msDao.getAllMealSittings();
		assertEquals(2, msList.get(0).getMeal_id());
	}
	
	@Test
	public void testDeleteUsersSittings()
	{
		User u = new User();
		u.setId(1);
		msDao.deleteUsersMealSittings(u);
		
		List<MealSitting> msList = msDao.getAllMealSittings();
		assertEquals(7, msList.get(1).getMeal_id());
	}
}
