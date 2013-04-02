package com.amc.foodecalc.repository;

import java.util.List;

import com.amc.foodecalc.domain.FoodPortion;
import com.amc.foodecalc.domain.User;
public interface FoodPortionDao {

	public List<FoodPortion> getAllFoodPortions();
	public List<FoodPortion> getUsersFoodPortions(User u);
	public List<FoodPortion> getFoodPortion(FoodPortion fp);
	public void addFoodPortion(FoodPortion fp);
	public void updateFoodPortion(FoodPortion fp);
	public void deleteFoodPortion(FoodPortion fp);
	public void deleteUsersFoodPortions(User u);
}
