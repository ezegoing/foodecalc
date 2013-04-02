package com.amc.foodecalc.domain;

import org.springframework.stereotype.Component;

@Component
public class FoodUnit {
	private int id;
	private String name;
	private int user_id;
	private int protein;
	private int carbs;
	private int fat;
	
	public FoodUnit()
	{}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}
	
	public int getCalories() {
		int calories = 0;
		
		int fatCalories = fat * 9;
		int carbCalories = carbs * 4;
		int proteinCalories = carbs * 4;
		
		return (fatCalories + carbCalories + proteinCalories);
	}
	
}
