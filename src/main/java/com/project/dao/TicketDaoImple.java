package com.project.dao;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.model.TheatreScreen;
import com.project.model.Tickets;

@Repository
public class TicketDaoImple implements TicketDao {
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	private static Logger logger = LogManager.getLogger(TicketDaoImple.class);

	public int insertTicket(Tickets t) {
		int t1 = 0;
		String SQL = "insert into tickets values (?,?,?,?,?,?)";
		Object[] params = { t.getId(), t.getNoOfSeats(), t.getTheatreScreenShows().getId(), t.getDate(), t.getStatus(),
				t.getTotalCost() };
		t1 = jdbcTemplateObject.update(SQL, params);
		logger.info("Inserted a record");
		return t1;
	}

	public int selectTotalSeat(int id) {
		String sql = "select * from theatre_screen where id=?";
		TheatreScreen ts = jdbcTemplateObject.queryForObject(sql, new Object[] { id }, new TicketMapper());

		return ts.getTotalTickets();
	}

	public int selectCost() {
		String sql = "select ticket_cost from theatre where id=?";
		int cost = (int) jdbcTemplateObject.queryForObject(sql, new Object[] { 1 }, Integer.class);
		return cost;
	}

	public int selectSeats(int id, LocalDate date) {
		String sql = "SELECT IF(SUM(no_of_seats) IS NOT NULL,SUM(no_of_seats),0) AS total_seats FROM tickets WHERE th_srn_shw_id =? AND date= ?";
		int seats = (int) jdbcTemplateObject.queryForObject(sql, new Object[] { id, date }, Integer.class);
		return seats;
	}

	public LocalTime selectStartTime(int id) {
		String sql = "select start_time from theatre_screen_shows where id=?";
		LocalTime lc = (LocalTime) jdbcTemplateObject.queryForObject(sql, new Object[] { id }, LocalTime.class);
		return lc;
	}

	public int getTicketId() {
		String sql = "select LAST_INSERT_ID()";
		int id = (int) jdbcTemplateObject.queryForObject(sql, Integer.class);
		return id;
	}

	public int cancelTicket(Tickets t) {
		String sql = "UPDATE tickets SET no_of_seats=?,status=?,totalcost=? WHERE id=?";
		Object[] params = { t.getNoOfSeats(), t.getStatus(), t.getTotalCost(), t.getId() };
		int t1 = jdbcTemplateObject.update(sql, params);
		logger.info("Booking cancel");
		return t1;
	}

	public LocalDate getDate(int id) {
		String sql = "select date from tickets where id=?";
		LocalDate lc = (LocalDate) jdbcTemplateObject.queryForObject(sql, new Object[] { id }, LocalDate.class);
		return lc;
	}
}
