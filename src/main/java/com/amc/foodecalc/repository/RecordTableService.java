package com.amc.foodecalc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amc.foodecalc.domain.Meal;


/**
 * Service for processing email 
 */
@Service("RecordTableService")
@Transactional
public class RecordTableService {

	//get log4j handler
	private static final Logger logger = Logger.getLogger(RecordTableService.class);
	
	private JdbcTemplate jdbcTemplate;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	/**
	 * Retrieves all Meal information
	 * 
	 * @param reproId
	 * @return a list of Meals
	 */
	public List<Meal> getAll() {
		
		// Prepare our SQL statement
		String sql = "select * from meal";

		// Maps a SQL result to a Java object
		RowMapper<Meal> mapper = new RowMapper<Meal>() {  
            public Meal mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Meal record = new Meal();
	        	
            	record.setId(rs.getInt("id"));
            	record.setName(rs.getString("name"));
            	record.setUser_id(rs.getInt("user_id"));
            			
	        	
	            return record;
	        }
	    };
		
	    // Retrieve all
		return jdbcTemplate.query(sql, mapper);
		
	}
	
	/**
	 * Adds a new record
	 * 
	 * @param firstName 
	 * @param lastName
      */
	/*public void add(String firstName, String lastName) {
		logger.info("Adding new email info to repro");

		// Save
		jdbcTemplate.update("insert into recordTable(firstName, lastName) values (?,?)",
				new Object[] {firstName, lastName  });

	}	*/
	

	/**
	 * Deletes existing email value 
	 * 	 
	 */
	/*public void deleteReproEmail(int recordId) {
		logger.info("Deleting existing record");
		
		// Prepare our SQL statement using Unnamed Parameters style
		String sql = "DELETE FROM recordTable where id=?";
		
        // Assign values to parameters
		Object[] parameters = new Object[] {recordId};
        
		// Delete
		jdbcTemplate.update(sql, parameters);
		
	}*/	
	
}

