package com.amc.foodecalc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.domain.MealPart;

@Service("JdbcMealPartDao")
@Transactional
public class JdbcMealPartDao implements MealPartDao {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private JdbcTemplate jdbcTemplate;
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    
	}
	
	public List<MealPart> getAllMealParts() {
		logger.info("Getting all Meal parts");
		
		String query = "SELECT * FROM meal_part";
		List<MealPart> mpList = jdbcTemplate.query(query, new MealPartMapper());
		
		return mpList;
	}

	public List<MealPart> getUsersMealParts(Meal m) {
		logger.info("Getting users Meal parts");
		
		String query = "SELECT * FROM Meal_part WHERE Meal_id=" + m.getId();
		List<MealPart> mpList = jdbcTemplate.query(query, new MealPartMapper());
		
		return mpList;
	}

	public List<MealPart> getMealPart(MealPart mp) {
		logger.info("Getting Meal part");
		
		String query = "SELECT * FROM Meal_part WHERE id=" + mp.getId();
		List<MealPart> mpList = (List<MealPart>) jdbcTemplate.query(query, new MealPartMapper());
		
		return mpList;
	}

	public void addMealPart(MealPart mp) {
		logger.info("Adding new Meal part");
		String query = "INSERT INTO Meal_part (Meal_id, food_portion_id) VALUES (?, ?)";

		int count = jdbcTemplate.update(query, 
		new Object[] {mp.getMeal_id(), mp.getFood_portion_id()});
		
		logger.info("Rows affected: " + count);

	}

	public void updateMealPart(MealPart mp) {
		
		logger.info("Updating Meal part");
		String query = "UPDATE Meal_part SET Meal_id=?, food_portion_id=?";

		int count = jdbcTemplate.update(query, 
		new Object[] {mp.getMeal_id(), mp.getFood_portion_id()});
		
		logger.info("Rows affected: " + count);

	}

	public void deleteMealPart(MealPart mp) {
		logger.info("Deleting Meal part");
		String query = "DELETE FROM Meal_part WHERE id=" + mp.getId();

		int count = jdbcTemplate.update(query);
		
		logger.info("Rows affected: " + count);

	}

	public void deleteMeal(Meal m) {
		logger.info("Deleting users Meal parts");
		String query = "DELETE FROM Meal_part WHERE Meal_id=" + m.getId();

		int count = jdbcTemplate.update(query);
		
		logger.info("Rows affected: " + count);

	}

	public static class MealPartMapper implements ParameterizedRowMapper<MealPart>
	{
		public MealPart mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			MealPart mp = new MealPart();
			mp.setId(rs.getInt("id"));
			mp.setFood_portion_id(rs.getInt("food_portion_id"));
			mp.setMeal_id(rs.getInt("Meal_id"));
						
			return mp;
		}
	}
}
