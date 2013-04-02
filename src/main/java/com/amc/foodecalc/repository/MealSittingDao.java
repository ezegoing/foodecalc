package com.amc.foodecalc.repository;

import java.util.List;

import com.amc.foodecalc.domain.MealSitting;
import com.amc.foodecalc.domain.User;

public interface MealSittingDao {

	public List<MealSitting> getAllMealSittings();
	public List<MealSitting> getUsersMealSittings(User u);
	public List<MealSitting> getMealSitting(MealSitting ms);
	public void addMealSitting(MealSitting ms);
	public void updateMealSitting(MealSitting ms);
	public void deleteMealSitting(MealSitting ms);
	public void deleteUsersMealSittings(User u);
}
