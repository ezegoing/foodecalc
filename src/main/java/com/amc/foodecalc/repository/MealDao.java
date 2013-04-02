package com.amc.foodecalc.repository;

import java.util.List;

import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.domain.User;

public interface MealDao {

	public List<Meal> getAllMeals();
	public List<Meal> getUserMeals(User u);
	public List<Meal> getMeal(Meal m);
	public void addMeal(Meal m);
	public void updateMeal(Meal m);
	public void deleteMeal(Meal m);
	public void deleteUsersMeals(User u);
}
