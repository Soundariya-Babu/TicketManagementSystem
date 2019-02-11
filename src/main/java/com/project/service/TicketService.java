package com.project.service;

import com.project.model.Tickets;
import com.project.service.exception.ServiceLayerException;
import com.project.service.exception.SuccessException;

public interface TicketService {
	
	public void insertT(Tickets t) throws ServiceLayerException, SuccessException;

	public void cancelTicketBooked(int id, int showId) throws ServiceLayerException, SuccessException;

}
