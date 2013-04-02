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

import com.amc.foodecalc.domain.FoodPortion;
import com.amc.foodecalc.domain.User;

@Service("JdbcFoodPortionDao")
@Transactional
public class JdbcFoodPortionDao implements FoodPortionDao {

	protected final Log logger = LogFactory.getLog(getClass());
			
	private JdbcTemplate jdbcTemplate;
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<FoodPortion> getAllFoodPortions() {
		logger.info("Getting all food portions");
		
		String query = "SELECT * FROM food_portion";
		List<FoodPortion> fpList = jdbcTemplate.query(query, new foodPortionMapper());
		
		return fpList;
	}

	public List<FoodPortion> getUsersFoodPortions(User u) {
		logger.info("Getting Users food portions");
		
		String query = "SELECT * FROM food_portion WHERE user_id=" + u.getId();
		List<FoodPortion> fpList = jdbcTemplate.query(query, new foodPortionMapper());
		
		return fpList;
	}

	public List<FoodPortion> getFoodPortion(FoodPortion fp) {
		logger.info("Getting food portion");
		
		String query = "SELECT * FROM food_portion WHERE id=" + fp.getId();
		List<FoodPortion> fp1 = jdbcTemplate.query(query, new foodPortionMapper());
		
		return fp1;
	}

	public void addFoodPortion(FoodPortion fp) {
		
		logger.info("Adding new food portion");
		String query = "INSERT INTO food_portion (name, user_id, food_unit_id, weight) VALUES (?, ?, ?, ?)";
				    		
		int count = jdbcTemplate.update(query, 
		new Object[] {fp.getName(), fp.getUser_id(), fp.getFood_unit_id(), fp.getWeight()});
		
		logger.info("Rows affected: " + count);
		
	}
	
		
	public void updateFoodPortion(FoodPortion fp) {
		
		logger.info("Updating food portion");
		String query = "UPDATE food_portion SET name=?, user_id=?, food_unit_id=?, weight=? WHERE id=?";
			
		
	    int count = jdbcTemplate.update(query, 
	    new Object[] {fp.getName(), fp.getUser_id(), fp.getFood_unit_id(), fp.getWeight(), fp.getId()});
		
		logger.info("Rows affected: " + count);
	}

	public void deleteFoodPortion(FoodPortion fp) {
		logger.info("Deleting food portion");
		String query = "DELETE from food_portion WHERE id=" + fp.getId();

	    int count = jdbcTemplate.update(query);
		
		logger.info("Rows affected: " + count);

	}

	public void deleteUsersFoodPortions(User u) {
		logger.info("Deleting Users food portions");
		String query = "DELETE from food_portion WHERE user_id=" + u.getId();

	    int count = jdbcTemplate.update(query);
		
		logger.info("Rows affected: " + count);

	}

	public static class foodPortionMapper implements ParameterizedRowMapper<FoodPortion>
	{
		public FoodPortion mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			FoodPortion fp = new FoodPortion();
			fp.setId(rs.getInt("id"));
			fp.setFood_unit_id(rs.getInt("food_unit_id"));
			fp.setName(rs.getString("name"));
			fp.setUser_id(rs.getInt("user_id"));
			fp.setWeight(rs.getInt("weight"));
			
			return fp;
		}
	}

	
	
}
