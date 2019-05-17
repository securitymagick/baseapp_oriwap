package com.securitymagick.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;

import com.securitymagick.domain.MenuItem;

@Component
@ComponentScan({"com.securitymagick"})
public class MenuDao {
	
	private static final class MenuItemMapper implements RowMapper<MenuItem> {

		public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			MenuItem menuItem = new MenuItem(rs.getString("menuHeading"), rs.getString("menuUrl"), rs.getString("menuGlyph"), rs.getString("menuPhrase"));
			return menuItem;
		}
	}

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public MenuDao() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.build();
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<MenuItem> getMenuDB(String type) {
		String sql = "SELECT * FROM menu where menuName='" + type + "'";
		Map<String, Object> params = new HashMap<>();
		return namedParameterJdbcTemplate.query(sql, params, new MenuItemMapper()  );
	}	

}
