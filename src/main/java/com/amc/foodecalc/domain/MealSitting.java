package com.amc.foodecalc.domain;

public class MealSitting {

	private int id;
	private int user_id;
	private int meal_id;
	private String date_and_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMeal_id() {
		return meal_id;
	}
	public void setMeal_id(int meal_id) {
		this.meal_id = meal_id;
	}
	public String getDate_and_time() {
		return date_and_time;
	}
	public void setDate_and_time(String date_and_time) {
		this.date_and_time = date_and_time;
	}
	
}
