package com.project.service;

import com.project.model.Tickets;

public interface TicketService {
	public String insertT(Tickets t);

	public String cancelTicketBooked(int id, int showId);

}
