package com.project.service;

import com.project.model.User;
import com.project.service.exception.ServiceLayerException;
import com.project.service.exception.SuccessException;

public interface TicketService {

	public void insertT(User user) throws ServiceLayerException, SuccessException;

	public void cancelTicketBooked(int id, int showId) throws ServiceLayerException, SuccessException;

}
