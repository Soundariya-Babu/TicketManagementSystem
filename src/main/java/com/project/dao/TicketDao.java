package com.project.dao;

import java.time.LocalTime;

import com.project.model.Tickets;

public interface TicketDao {
	public int insertTicket(Tickets t);
	public int selectSeats(int id);
	public int selectTotalSeat(int id);
	public int selectCost();
	public LocalTime selectStartTime(int id);
	public int getTicketId();
	public int cancelTicket(Tickets t);

	
}
