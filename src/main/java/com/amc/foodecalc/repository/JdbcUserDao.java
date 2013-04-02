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

import com.amc.foodecalc.domain.User;

@Service("JdbcUserDao")
@Transactional
public class JdbcUserDao implements UserDao {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private JdbcTemplate jdbcTemplate;
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    
	}
	
	public List<User> getAllUsers() {
		logger.info("Getting all users");
		
		//String query = "SELECT * FROM users";
		//String query = "Select users.username, users.email, authorities.authority" +
		//		"FROM users" +
		//		"JOIN authorities on users.username = authorities.username";
		
		
		String query = "SELECT user.id, user.username, user.email, authorities.authority, users.enabled, users.password" +
		" FROM user" +
		" LEFT JOIN authorities ON authorities.username = user.username" +
		" LEFT JOIN users ON users.username = user.username";
		
		List<User> uList = jdbcTemplate.query(query, new userMapper());
		
		return uList;
	}

	public List<User> getUser(User u) {
		logger.info("Getting user");
		
		//String query = "SELECT * FROM users WHERE username=" + u.getUserName();
		//String query = "Select users.username, users.email, authorities.authority" +
		//		"FROM users" +
		//		"JOIN authorities on users.username = authorities.username" +
		//		"WHERE users.username = " + u.getUserName();
		
		String query = "SELECT user.id, user.username, user.email, authorities.authority, users.enabled, users.password" +
				" FROM user" +
				" LEFT JOIN authorities ON authorities.username = user.username" +
				" LEFT JOIN users ON users.username = user.username" +
				" WHERE user.username = '" + u.getUserName() + "'";
				
		
		List<User> uList = (List<User>) jdbcTemplate.query(query, new userMapper());
		
		return uList;
	}

	public void addUser(User u) {
		logger.info("Adding new user");
		String query = "INSERT INTO users(username, password, enabled) VALUES (?, ?, ?)";
		int count = jdbcTemplate.update(query, 
		new Object[] {u.getUserName(), u.getPassword(), u.getEnabled()});
		logger.info("Rows affected: " + count);

		logger.info("Adding authority for user");
		query = "INSERT INTO authorities(username, authority) VALUES(?, ?)";
		count = jdbcTemplate.update(query,
		new Object[] {u.getUserName(), u.getAuthority()});
		logger.info("Rows affected: " + count);
		
		logger.info("Adding id/email for user");
		query = "INSERT INTO user (username, email) VALUES (?, ?)";
		count = jdbcTemplate.update(query,
		new Object[] {u.getUserName(), u.getEmail()});
		logger.info("Rows affected: " + count);
	}

	public void updateUser(User u) {
		
		logger.info("Updating user security");
		String query = "UPDATE users SET password=?, enabled=? WHERE username=?";
		int count = jdbcTemplate.update(query, 
		new Object[] {u.getPassword(), u.getEnabled(), u.getUserName()});
		logger.info("Rows affected: " + count);

		logger.info("Updating users access");
		query = "UPDATE authorities SET authority=? WHERE username=?";
		count = jdbcTemplate.update(query,
		new Object[] {u.getAuthority(), u.getUserName()});
		logger.info("Rows affected: " + count);
		
		logger.info("Updating user details");
		query = "UPDATE user SET email=? WHERE id=?";
		count = jdbcTemplate.update(query,
		new Object[] {u.getEmail(), u.getId()});
		logger.info("Rows affected: " + count);
	}

	public void deleteUser(User u) {
		logger.info("Deleting user");
		String query = "DELETE FROM user WHERE id=" + u.getId();
		int count = jdbcTemplate.update(query);
		logger.info("Rows affected: " + count);
		
		logger.info("Deleting security");
		query = "DELETE FROM users WHERE username='" + u.getUserName() + "'";
		count = jdbcTemplate.update(query);
		logger.info("Rows affected: " + count);
		
		logger.info("Deleting access");
		query = "DELETE FROM authorities WHERE username='" + u.getUserName() + "'";
		count = jdbcTemplate.update(query);
		logger.info("Rows affected: " + count);
	}

	public static class userMapper implements ParameterizedRowMapper<User>
	{
		public User mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setUserName(rs.getString("username"));
			u.setEmail(rs.getString("email"));
			u.setPassword("password");
			u.setAuthority("authority");
			u.setEnabled(rs.getInt("enabled"));
			
			return u;
		}
	}
	
}
