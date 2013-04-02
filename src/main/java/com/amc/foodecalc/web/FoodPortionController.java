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

import com.amc.foodecalc.domain.FoodPortion;
import com.amc.foodecalc.domain.FoodUnit;
import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.domain.User;
import com.amc.foodecalc.repository.JdbcFoodPortionDao;
import com.amc.foodecalc.repository.JdbcMealDao;
import com.amc.foodecalc.repository.JdbcFoodUnitDao;

@Controller
public class FoodPortionController {

private static final Logger logger = LoggerFactory.getLogger(FoodPortionController.class);
	
	@Resource(name = "JdbcFoodPortionDao")
	private JdbcFoodPortionDao jdbcFoodPortionDao;
	
	@Resource(name = "JdbcFoodUnitDao")
	private JdbcFoodUnitDao jdbcFoodUnitDao;
	
	@RequestMapping(value="secure/foodPortions/save", method = RequestMethod.POST)
	public String save(@CookieValue("userid") String userid, @RequestParam("id") int food_unit_id, @RequestParam("name") String name, @RequestParam("weight") int weight, Model model)
	{
		FoodPortion fp = new FoodPortion();
		fp.setFood_unit_id(food_unit_id);
		fp.setName(name);
		fp.setWeight(weight);
		fp.setUser_id(Integer.parseInt(userid));
		
		jdbcFoodPortionDao.addFoodPortion(fp);
		
		User u = new User();
		u.setId(Integer.parseInt(userid));
		
		List<FoodPortion> fpList = jdbcFoodPortionDao.getUsersFoodPortions(u);
		model.addAttribute("fpList", fpList);
		
		return "secure/foodPortions";
	}
	
	@RequestMapping(value="secure/foodPortions/new", method = RequestMethod.GET)
	public String newPortion(Model model)
	{
		return "secure/newFoodPortion";
	}
	
	@RequestMapping(value = "secure/foodPortions/newUnit", method = RequestMethod.GET)
	public String newPortionUnit(@RequestParam("id") int id, Model model)
	{
		FoodPortion fp = new FoodPortion();
		FoodUnit fu = jdbcFoodUnitDao.getFoodUnit(id);
		fp.setFood_unit_id(id);
		
		model.addAttribute("fp", fp);
		model.addAttribute("fu", fu);
		
		return "secure/newFoodPortion";
	}
	
	@RequestMapping(value = "secure/foodPortions/viewAll", method = RequestMethod.GET)
	public String ViewAll(@CookieValue("userid") String userid, Model model)
	{
		User u = new User();
		u.setId(Integer.parseInt(userid));
		
		List<FoodPortion> fpList = jdbcFoodPortionDao.getUsersFoodPortions(u);
		
		
		for(int i = 0; i < fpList.size(); i++) {
			Double unitCalories = (double) jdbcFoodUnitDao.getFoodUnit(fpList.get(i).getFood_unit_id()).getCalories();
			Double unit = unitCalories / 100;
			int totalCalories = (int)(unit * fpList.get(i).getWeight());
			fpList.get(i).setCalories(totalCalories);
		}
		
		model.addAttribute("fpList", fpList);
		
		return "secure/foodPortions";
	}
	
	@RequestMapping(value = "secure/foodPortions/saveEdit", method = RequestMethod.POST)
	public String Save(@CookieValue("userid") String userid, @RequestParam("id") int id, @RequestParam("food_unit_id") int food_unit_id, @RequestParam("user_id") int user_id, @RequestParam("name") String name, @RequestParam("weight") int weight, Model model)
	{
		
		FoodPortion fp = new FoodPortion();
		fp.setId(id);
		fp.setName(name);
		fp.setFood_unit_id(food_unit_id);
		fp.setUser_id(user_id);
		fp.setWeight(weight);
		
		jdbcFoodPortionDao.updateFoodPortion(fp);
		
		User u = new User();
		u.setId(user_id);
		
		List<FoodPortion> fpList = jdbcFoodPortionDao.getUsersFoodPortions(u);
		model.addAttribute("fpList", fpList);
		
		return "secure/foodPortions";
	}
	
	@RequestMapping(value = "secure/foodPortions/edit", method = RequestMethod.GET)
	public String Edit(@RequestParam("id") int id, Model model)
	{
		FoodPortion fp = new FoodPortion();
		fp.setId(id);
		List<FoodPortion> fpList = jdbcFoodPortionDao.getFoodPortion(fp);
		model.addAttribute("fpList", fpList);
		
		return "secure/editFoodPortion";
	}
	
}
