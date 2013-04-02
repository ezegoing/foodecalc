package com.amc.foodecalc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.domain.User;

@Service("JdbcMealDao")
@Transactional
public class JdbcMealDao implements MealDao {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private JdbcTemplate jdbcTemplate;
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    
	}
		
	public List<Meal> getAllMeals() {
		logger.info("Getting all Meals");
		
		String query = "SELECT * FROM Meal";
		List<Meal> mList = jdbcTemplate.query(query, new MealMapper());
						
		return mList;
	}

	public List<Meal> getUserMeals(User u) {
		logger.info("Getting users Meals");
		
		String query = "SELECT * FROM Meal WHERE user_id=" + u.getId();
		List<Meal> mList = jdbcTemplate.query(query, new MealMapper());
		
		return mList;
	}

	public List<Meal> getMeal(Meal m) {
		logger.info("Getting Meal");
		
		String query = "SELECT * FROM Meal WHERE id=" + m.getId();
		List<Meal> mList = (List<Meal>) jdbcTemplate.query(query, new MealMapper());
		
		return mList;
	}

	public void addMeal(Meal m) {
		logger.info("Adding new Meal");
		String query = "INSERT INTO Meal (user_id, name) VALUES (?, ?)";
				
		int count = jdbcTemplate.update(query, 
			new Object[] {m.getUser_id(), m.getName()});
		
		logger.info("Rows affected: " + count);

	}

	public void updateMeal(Meal m) {
		logger.info("Updating Meal");
		String query = "UPDATE Meal SET user_id=?, name=? WHERE id=?";
				
		int count = jdbcTemplate.update(query, 
				new Object[] {m.getUser_id(), m.getName(), m.getId()});
		
		logger.info("Rows affected: " + count);

	}

	public void deleteMeal(Meal m) {
		logger.info("Deleting Meal");
		String query = "DELETE FROM Meal WHERE id=?";
				
		int count = jdbcTemplate.update(query, 
				new Object[] {m.getId()});
		
		logger.info("Rows affected: " + count);

	}

	public void deleteUsersMeals(User u) {
		logger.info("Deleting users Meals");
		String query = "DELETE FROM Meal WHERE user_id=?";
				
		int count = jdbcTemplate.update(query, 
		new Object[] {u.getId()});
		
		logger.info("Rows affected: " + count);

	}
	
	public static class MealMapper implements ParameterizedRowMapper<Meal>
	{
		public Meal mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Meal m = new Meal();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setUser_id(rs.getInt("user_id"));
						
			return m;
		}
	}

	

}
