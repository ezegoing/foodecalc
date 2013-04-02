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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amc.foodecalc.repository.*;
import com.amc.foodecalc.domain.*;

@Controller
public class MealController {

	private static final Logger logger = LoggerFactory.getLogger(FoodUnitController.class);
	
	@Resource(name = "JdbcMealDao")
	private JdbcMealDao jdbcMealDao;
	
	@RequestMapping(value = "secure/meals/viewAll", method = RequestMethod.GET)
	public String ViewAll(@CookieValue("userid") String userid, Model model)
	{
		User u = new User();
		u.setId(Integer.parseInt(userid));
		
		List<Meal> meals = jdbcMealDao.getUserMeals(u);
		
		return "secure/meals";
	}
	
	@RequestMapping(value = "secure/meals/new", method = RequestMethod.GET)
	public String New(@CookieValue("userid") String userid, Model model)
	{
		User u = new User();
		u.setId(Integer.parseInt(userid));
		
		List<Meal> meals = jdbcMealDao.getUserMeals(u);
		
		return "secure/newMeal";
	}
}
