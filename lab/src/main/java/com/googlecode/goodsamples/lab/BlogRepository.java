package com.googlecode.goodsamples.lab;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BlogRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Visitor> findAll() {
		final List<Visitor> result = new ArrayList<Visitor>();

		jdbcTemplate.query("SELECT * FROM visitor", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.add(new Visitor(rs.getString(1), rs.getString(2)));
			}
		});
		
		return result;
	}

}
