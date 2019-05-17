package com.securitymagick.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;

import com.securitymagick.domain.RegistrationForm;
import com.securitymagick.domain.User;

import static com.securitymagick.AppConstants.userFavoriteSqlInjection;


@Component
@ComponentScan({"com.securitymagick"})
public class UserDao {

	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public UserDao() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.build();
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<User> getUsers() {
		String sql = "SELECT * FROM users";
		Map<String, Object> params = new HashMap<>();
		return namedParameterJdbcTemplate.query(sql, params, new UserMapper()  );

	}
	
	public List<User> getUser(String username) {
		String sql = "SELECT * FROM users WHERE username='" + username + "'";
		Map<String, Object> params = new HashMap<>();
		return namedParameterJdbcTemplate.query(sql, params, new UserMapper()  );	
	}
	
	public List<User> getUserById(Integer id) {
		String sql = "SELECT * FROM users WHERE id=" + id;
		Map<String, Object> params = new HashMap<>();
		return namedParameterJdbcTemplate.query(sql, params, new UserMapper()  );	
	}	
	
	public Integer getNextUserId() {
		String sql = "SELECT MAX(id) FROM users";
		Map<String, Object> params = new HashMap<>();
		Integer maxId = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
		return maxId + 1;
	}	
	
	public void addUser(RegistrationForm u) {
		Integer userId = this.getNextUserId();
		Map<String, Object> params = new HashMap<>();
		String sql = "INSERT INTO users VALUES (" +  userId + ", '" + u.getUsername() + "', '" + u.getPassword() + "', '" + u.getFavorite() +  "', 1, 0)";
		namedParameterJdbcTemplate.update(sql, params);
	}	
	
	public void updateUser(User u) {
		Map<String, Object> params = new HashMap<>();
		String sql = "UPDATE users SET favorite='" + u.getFavorite() + "', password='" + u.getPassword() + "', isUser=" + u.getIsUser() + ", isAdmin=" + u.getIsAdmin() + " WHERE id =" +  u.getId().toString();
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	public void updatePassword(String username, String password) {
		Map<String, Object> params = new HashMap<>();
		String sql = "UPDATE users SET password='" + password + "' WHERE username = '" + username + "'";
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	public void deleteUser(Integer userId) {
		String sql = "DELETE FROM users WHERE id = " + userId.toString();
		Map<String, Object> params = new HashMap<>();
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setFavorite(rs.getString("favorite"));
			user.setIsUser(rs.getInt("isUser"));
			user.setIsAdmin(rs.getInt("isAdmin"));
			return user;
		}
	}

}