package com.amc.foodecalc.repository;

import java.util.List;

import com.amc.foodecalc.domain.User;

public interface UserDao {

	public List<User> getAllUsers();
	public List<User> getUser(User u);
	public void addUser(User u);
	public void updateUser(User u);
	public void deleteUser(User u);
}
