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

import com.amc.foodecalc.domain.FoodUnit;
import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.domain.User;
import com.amc.foodecalc.repository.JdbcFoodUnitDao;
import com.amc.foodecalc.repository.JdbcMealDao;
@Controller
public class FoodUnitController {

	private static final Logger logger = LoggerFactory.getLogger(FoodUnitController.class);
	
	@Resource(name = "JdbcFoodUnitDao")
	private JdbcFoodUnitDao jdbcFoodUnitDao;
	
	@RequestMapping(value = "secure/foodUnits/select/portion", method = RequestMethod.GET)
	public String Select(Model model)
	{
		List<FoodUnit> fuList = jdbcFoodUnitDao.getAllFoodUnits();
		model.addAttribute("fuList", fuList);
		
		return "secure/foodUnitPortion";
	}
	
	@RequestMapping(value = "secure/foodUnits/viewAll", method = RequestMethod.GET)
	public String ViewAll(Model model, @CookieValue("userid") String userid)
	{
		List<FoodUnit> fuList = jdbcFoodUnitDao.getAllFoodUnits();
		model.addAttribute("fuList", fuList);
		model.addAttribute("userid", userid);
		
		return "secure/foodUnits";
	}
	
	@RequestMapping(value = "secure/foodUnits/new", method = RequestMethod.GET)
	public String New(Model model)
	{
		model.addAttribute(new FoodUnit());
		return "secure/newFoodUnit";
	}
	
	@RequestMapping(value = "secure/foodUnits/new", method = RequestMethod.POST)
	public String NewSave(Model model, FoodUnit foodUnit)
	{
		jdbcFoodUnitDao.addFoodUnit(foodUnit);
		
		List<FoodUnit> fuList = jdbcFoodUnitDao.getAllFoodUnits();
		model.addAttribute("fuList", fuList);
		
		return "secure/foodUnits";
	}
	
	@RequestMapping(value = "secure/foodUnits/save", method = RequestMethod.POST)
	public String Save(@RequestParam("name") String name, @RequestParam("protein") int protein, @RequestParam("carbs") int carbs, @RequestParam("fat") int fat, Model model)
	{
		
		FoodUnit fu = new FoodUnit();
		fu.setName(name);
		fu.setProtein(protein);
		fu.setCarbs(carbs);
		fu.setFat(fat);
		
		jdbcFoodUnitDao.addFoodUnit(fu);
		
		List<FoodUnit> fuList = jdbcFoodUnitDao.getAllFoodUnits();
		model.addAttribute("fuList", fuList);
		
		return "secure/foodUnits";
	}
	
	@RequestMapping(value = "secure/foodUnits/saveEdit", method = RequestMethod.POST)
	public String Save(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("protein") int protein, @RequestParam("carbs") int carbs, @RequestParam("fat") int fat, Model model)
	{
		
		FoodUnit fu = new FoodUnit();
		fu.setId(id);
		fu.setName(name);
		fu.setProtein(protein);
		fu.setCarbs(carbs);
		fu.setFat(fat);
		
		jdbcFoodUnitDao.updateFoodUnit(fu);
		
		List<FoodUnit> fuList = jdbcFoodUnitDao.getAllFoodUnits();
		model.addAttribute("fuList", fuList);
		
		return "secure/foodUnits";
	}
	
	@RequestMapping(value = "secure/foodUnits/edit", method = RequestMethod.GET)
	public String Edit(@RequestParam("id") int id, Model model)
	{
		FoodUnit fu = new FoodUnit();
		fu.setId(id);
		fu = jdbcFoodUnitDao.getFoodUnit(fu);
		model.addAttribute("fu", fu);
		
		return "secure/editFoodUnit";
	}
	
}
