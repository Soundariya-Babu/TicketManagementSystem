package com.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.model.TheatreScreen;

public class TicketMapper implements RowMapper<TheatreScreen> {

	public TheatreScreen mapRow(ResultSet rs, int rowNum) throws SQLException {
		TheatreScreen ts = new TheatreScreen();
		ts.setId(rs.getInt("id"));
		ts.setName(rs.getString("name"));
		ts.setTotalTickets(rs.getInt("total_tickets"));
		return ts;
	}

}
