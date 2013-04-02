package com.amc.foodecalc.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amc.foodecalc.domain.FoodUnit;
import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.domain.User;
import com.amc.foodecalc.repository.JdbcFoodUnitDao;
import com.amc.foodecalc.repository.JdbcMealDao;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name = "JdbcMealDao")
	private JdbcMealDao jdbcMealDao;
	
	@Resource(name = "JdbcFoodUnitDao")
	private JdbcFoodUnitDao jdbcFoodUnitDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String getData(Model model)
	{
		logger.info("Welcome to getData");
		
		//JdbcMealDao mDao = new JdbcMealDao();
		List<Meal> mList = jdbcMealDao.getAllMeals();
		model.addAttribute("fuList", mList);
		
		User u = new User();
		u.setUserName("");
		List<Meal> uList = jdbcMealDao.getUserMeals(u);
		model.addAttribute("userList", uList);
		
		Meal m = new Meal();
		m.setId(1);
		List<Meal> m1List = jdbcMealDao.getMeal(m);
		model.addAttribute("mealList", m1List);
				
		return "getData";
	}
	
	@RequestMapping(value = "/addMeal", method = RequestMethod.GET)
	public String addMeal(@RequestParam("name") String name,@RequestParam("user_id") String user_id,Model model)
	{
		Meal m = new Meal();
		m.setName(name);
		m.setUser_id(Integer.parseInt(user_id));
		jdbcMealDao.addMeal(m);
		
		List<Meal> mList = jdbcMealDao.getAllMeals();
		model.addAttribute("fuList", mList);
		
		return "showMeals";
	}
	
	
}
