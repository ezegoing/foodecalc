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

import com.amc.foodecalc.domain.FoodUnit;
import com.amc.foodecalc.domain.User;

@Service("JdbcFoodUnitDao")
@Transactional
public class JdbcFoodUnitDao implements FoodUnitDao {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private JdbcTemplate jdbcTemplate;
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    
	}
	
	public FoodUnit getFoodUnit(FoodUnit fu)
	{
		logger.info("Getting food unit");
		String query = "SELECT * from food_unit WHERE id=" + fu.getId();
		FoodUnit unit = jdbcTemplate.queryForObject(query, new FoodUnitMapper());
		
		return unit;
	}
	
	public FoodUnit getFoodUnit(int id)
	{
		logger.info("Getting food unit");
		String query = "SELECT * from food_unit WHERE id=" + id;
		FoodUnit unit = jdbcTemplate.queryForObject(query, new FoodUnitMapper());
		
		return unit;
	}
	
	public List<FoodUnit> getAllFoodUnits()
	{
		logger.info("Getting all food units");
		String query = "SELECT * from food_unit";
		List<FoodUnit> fuList = jdbcTemplate.query(query, new FoodUnitMapper());
		
		return fuList;
	}
	
	public void addFoodUnit(FoodUnit fu)
	{
		logger.info("Adding new food unit");
		//String query = "INSERT INTO food_unit (name, user_id, protein, carbs, fat) VALUES (:name, :user_id, :protein, :carbs, :fat)";
		String query = "INSERT INTO food_unit (name, user_id, protein, carbs, fat) values(?, ?, ?, ?, ?)";
		
		int count = jdbcTemplate.update(query, 
		new Object[] {fu.getName(), fu.getUser_id(), fu.getProtein(), fu.getCarbs(), fu.getFat()});
		
		logger.info("Rows affected: " + count);
	}
	
	public void updateFoodUnit(FoodUnit fu)
	{
		logger.info("Updating food unit");
		String query = "UPDATE food_unit SET name=?, user_id=?, protein=?, carbs=?, fat=? WHERE id=?";

		int count = jdbcTemplate.update(query,
			new Object[] {fu.getName(), fu.getUser_id(), fu.getProtein(), fu.getCarbs(), fu.getFat(), fu.getId()});
		
		logger.info("Rows affected: " + count);
	}
	
	public void deleteUsersFoodUnit(User u)
	{
		logger.info("Deleting users food units");
		String query = "DELETE from food_unit WHERE user_id=" + u.getId();
		
		int count = jdbcTemplate.update(query);
		
		logger.info("Rows affected: " + count);
	}
	
	public void deleteFoodUnit(FoodUnit fu)
	{
		logger.info("Deleting food unit");
		String query = "DELETE from food_unit WHERE id=" + fu.getId();

		int count = jdbcTemplate.update(query);
		
		logger.info("Rows affected: " + count);
	}
	
	public static class FoodUnitMapper implements ParameterizedRowMapper<FoodUnit>
	{
		public FoodUnit mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			FoodUnit fu = new FoodUnit();
			fu.setId(rs.getInt("id"));
			fu.setName(rs.getString("name"));
			fu.setUser_id(rs.getInt("user_id"));
			fu.setProtein(rs.getInt("protein"));
			fu.setCarbs(rs.getInt("carbs"));
			fu.setFat(rs.getInt("fat"));
			
			return fu;
		}
	}
}
