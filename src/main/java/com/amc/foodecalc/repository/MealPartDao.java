package com.amc.foodecalc.repository;

import java.util.List;

import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.domain.MealPart;

public interface MealPartDao {

	public List<MealPart> getAllMealParts();
	public List<MealPart> getUsersMealParts(Meal m);
	public List<MealPart> getMealPart(MealPart mp);
	public void addMealPart(MealPart mp);
	public void updateMealPart(MealPart mp);
	public void deleteMealPart(MealPart mp);
	public void deleteMeal(Meal m);
	
}
