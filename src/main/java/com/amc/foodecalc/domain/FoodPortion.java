package com.amc.foodecalc.domain;

import javax.annotation.Resource;

import com.amc.foodecalc.repository.JdbcFoodUnitDao;

public class FoodPortion {

	private int id;
	private int food_unit_id;
	private int user_id;
	private String name;
	private int weight;
	private int calories;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFood_unit_id() {
		return food_unit_id;
	}
	public void setFood_unit_id(int food_unit_id) {
		this.food_unit_id = food_unit_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getCalories() {
		return this.calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
}
