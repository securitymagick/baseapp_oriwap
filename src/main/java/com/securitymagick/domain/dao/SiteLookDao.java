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

import com.securitymagick.domain.MenuItem;
import com.securitymagick.domain.SiteLook;

@Component
@ComponentScan({"com.securitymagick"})
public class SiteLookDao {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public SiteLookDao() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
	    		.setType(EmbeddedDatabaseType.H2)
	    		.build();
			namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<SiteLook> getSiteLook() {
		String sql = "SELECT * FROM sitelook where id=1";
		Map<String, Object> params = new HashMap<>();
		return namedParameterJdbcTemplate.query(sql, params, new SiteLookMapper()  );	
	}
	
	private static final class SiteLookMapper implements RowMapper<SiteLook> {

		public SiteLook mapRow(ResultSet rs, int rowNum) throws SQLException {
			SiteLook siteLook = new SiteLook();
			siteLook.setTitle(rs.getString("title"));
			siteLook.setFontColor(rs.getString("fontColor"));
			siteLook.setGlyphSize(new Integer(rs.getInt("glyphSize")).toString());
			siteLook.setLogoSize(new Integer(rs.getInt("logoSize")).toString());
			siteLook.setMenuCols(rs.getString("menuCols"));
			siteLook.setPreLogoText(rs.getString("preLogoText"));
			siteLook.setLogoGlyphOne(rs.getString("logoGlyphOne"));
			siteLook.setLogoGlyph2(rs.getString("logoGlyph2"));
			siteLook.setLogoGlyph3(rs.getString("logoGlyph3"));
			siteLook.setLogoGlyph4(rs.getString("logoGlyph4"));
			siteLook.setLabelCols(rs.getString("labelCols"));
			siteLook.setFormCols(rs.getString("formCols"));
			siteLook.setUrl(rs.getString("url"));
			siteLook.setYear(new Integer(rs.getInt("year")).toString());
			siteLook.setFavorite(rs.getString("favorite"));
			siteLook.setAddWhat(rs.getString("addWhat"));
			return siteLook;
		}
	}
}
