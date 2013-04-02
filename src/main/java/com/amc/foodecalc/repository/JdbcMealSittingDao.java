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

import com.amc.foodecalc.domain.MealSitting;
import com.amc.foodecalc.domain.User;

@Service("JdbcMealSittingDao")
@Transactional
public class JdbcMealSittingDao implements MealSittingDao {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private JdbcTemplate jdbcTemplate;
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    
	}
	
	public List<MealSitting> getAllMealSittings() {
		logger.info("Getting all meal sittings");
		
		String query = "SELECT * FROM meal_sitting";
		List<MealSitting> msList = jdbcTemplate.query(query, new MealSittingMapper());
		
		return msList;
	}

	public List<MealSitting> getUsersMealSittings(User u) {
		logger.info("Getting users meal sittings");
		
		String query = "SELECT * FROM meal_sitting WHERE user_id=" + u.getId();
		List<MealSitting> msList = jdbcTemplate.query(query, new MealSittingMapper());
		
		return msList;
	}

	public List<MealSitting> getMealSitting(MealSitting ms) {
		logger.info("Getting meal sitting");
		
		String query = "SELECT * FROM meal_sitting WHERE id=" + ms.getId();
		List<MealSitting> msList = (List<MealSitting>) jdbcTemplate.query(query, new MealSittingMapper());
		
		return msList;
	}

	public void addMealSitting(MealSitting ms) {
		logger.info("Adding new meal sitting");
		String query = "INSERT INTO meal_sitting (user_id, meal_id, date_and_time) VALUES (?, ?, ?)";
		
		int count = jdbcTemplate.update(query, 
		new Object[] {ms.getUser_id(), ms.getMeal_id(), ms.getDate_and_time()});
		
		logger.info("Rows affected: " + count);

	}

	public void updateMealSitting(MealSitting ms) {
		logger.info("Updating meal sitting");
		String query = "UPDATE meal_sitting SET user_id=?, meal_id=?, date_and_time=? WHERE id=?";
		
		int count = jdbcTemplate.update(query,
		new Object[] {ms.getUser_id(), ms.getMeal_id(), ms.getDate_and_time(), ms.getId()});
		
		logger.info("Rows affected: " + count);

	}

	public void deleteMealSitting(MealSitting ms) {
		logger.info("Deleting meal sitting");
		String query = "DELETE FROM meal_sitting WHERE id=" + ms.getId();
		
		int count = jdbcTemplate.update(query);
		
		logger.info("Rows affected: " + count);

	}

	public void deleteUsersMealSittings(User u) {
		logger.info("Deleting users meal sittings");
		String query = "DELETE FROM meal_sitting WHERE user_id=" + u.getId();
		
		int count = jdbcTemplate.update(query);
		
		logger.info("Rows affected: " + count);

	}

	public static class MealSittingMapper implements ParameterizedRowMapper<MealSitting>
	{
		public MealSitting mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			MealSitting ms = new MealSitting();
			ms.setId(rs.getInt("id"));
			ms.setUser_id(rs.getInt("user_id"));
			ms.setMeal_id(rs.getInt("meal_id"));
			ms.setDate_and_time(rs.getString("date_and_time"));	
			return ms;
		}
	}
	
}
