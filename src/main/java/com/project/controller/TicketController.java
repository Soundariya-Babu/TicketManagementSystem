package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.model.TheatreScreenShows;
import com.project.model.Tickets;
import com.project.model.User;
import com.project.service.TicketService;
import com.project.service.exception.ServiceLayerException;
import com.project.service.exception.SuccessException;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;

	@PostMapping("/book")
	public ResponseEntity<String> store(@RequestBody User user) {
		try {
			ticketService.insertT(user);
		} catch (SuccessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		} catch (ServiceLayerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/cancel")
	public ResponseEntity<String> cancelTicket(@RequestBody User user) {
		try {
			ticketService.cancelTicketBooked(user.getId(), user.getShowId());
		} catch (ServiceLayerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (SuccessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
