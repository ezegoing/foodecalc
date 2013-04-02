package com.amc.foodecalc.repository;

import java.util.List;

import com.amc.foodecalc.domain.FoodUnit;
import com.amc.foodecalc.domain.User;
public interface FoodUnitDao {
	public List<FoodUnit> getAllFoodUnits();
	public FoodUnit getFoodUnit(FoodUnit fu);
	public FoodUnit getFoodUnit(int id);
	public void updateFoodUnit(FoodUnit fu);
	public void addFoodUnit(FoodUnit fu);
	public void deleteFoodUnit(FoodUnit fu);
	public void deleteUsersFoodUnit(User u);
}
