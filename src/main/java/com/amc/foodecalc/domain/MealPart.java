package com.amc.foodecalc.domain;

public class MealPart {
	private int id;
	private int meal_id;
	private int food_portion_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMeal_id() {
		return meal_id;
	}
	public void setMeal_id(int meal_id) {
		this.meal_id = meal_id;
	}
	public int getFood_portion_id() {
		return food_portion_id;
	}
	public void setFood_portion_id(int food_portion_id) {
		this.food_portion_id = food_portion_id;
	}
	
	
}
