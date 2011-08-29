package com.googlecode.goodsamples.lab;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BlogRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Visitor> findAllVisitors() {
		final List<Visitor> result = new ArrayList<Visitor>();

		jdbcTemplate.query("SELECT * FROM visitor", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.add(new Visitor(rs.getString(1), rs.getString(2)));
			}
		});

		return result;
	}

	public List<Article> findAllArticles() {
		final List<Article> result = new ArrayList<Article>();

		jdbcTemplate.query("SELECT * FROM article", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.add(new Article(rs.getString(1), rs.getString(2), rs
						.getDate(3), rs.getInt(4)));
			}
		});

		return result;
	}

	public Profile findProfile() {
		return jdbcTemplate.queryForObject("SELECT name,age,hobby FROM profile", new RowMapper<Profile>() {
			@Override
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Profile(rs.getString(1), rs.getInt(2), rs.getString(3));
			}
		});
	}

	public List<Comment> findComments() {
		final List<Comment> result = new ArrayList<Comment>();

		jdbcTemplate.query("SELECT * FROM comment", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.add(new Comment(rs.getString(1), rs.getDate(2)));
			}
		});

		return result;
	}
}
