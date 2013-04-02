package com.amc.foodecalc.web;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amc.foodecalc.domain.FoodUnit;
import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.domain.User;
import com.amc.foodecalc.repository.JdbcFoodUnitDao;
import com.amc.foodecalc.repository.JdbcMealDao;
import com.amc.foodecalc.repository.JdbcUserDao;
@Controller
public class LoginController {

	@Autowired
	private StandardPasswordEncoder passwordEncoder;
	
	@Resource(name = "JdbcUserDao")
	private JdbcUserDao jdbcUserDao;
	
	@RequestMapping(value="newuser", method = RequestMethod.GET)
	public String registerSetup(Model model)
	{
		return "register";
	}
	
	@RequestMapping(value="register", method = RequestMethod.POST)
	public String register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, Model model)
	{
		String encodedPassword = generateEncryptedPassword(password);
		User u = new User();
		u.setUserName(username);
		u.setPassword(encodedPassword);
		u.setEmail(email);
		u.setEnabled(1);
		u.setAuthority("ROLE_USER");
		jdbcUserDao.addUser(u);
		
		return "home";
	}
	
	private String generateEncryptedPassword(String clearPassword) {
        String encodedPassword = passwordEncoder.encode(clearPassword);
        return encodedPassword;
    }

	
	@RequestMapping(value = "/secure/dash", method = RequestMethod.GET)
	public String secure(Model model, Principal principal, HttpServletResponse response)
	{
		String name = principal.getName();
		
		User u = new User();
		u.setUserName(name);
		List<User> users = jdbcUserDao.getUser(u);
		
		String id = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		model.addAttribute("pageTitle", "Dashboard");
		
		response.addCookie(new Cookie("userid", users.get(0).getId() + ""));
		
		return "secure/dash";
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "home";
 
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "home";
 
	}

}
