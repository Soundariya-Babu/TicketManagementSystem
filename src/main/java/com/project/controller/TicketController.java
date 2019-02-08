package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.model.TheatreScreenShows;
import com.project.model.Tickets;
import com.project.model.User;
import com.project.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;

	@PostMapping("/insert")
	public String store(@RequestBody User user) {
		TheatreScreenShows tss = new TheatreScreenShows();
		tss.setId(user.getShowId());
        Tickets t = new Tickets(user.getNoOfSeats(), tss, user.getDate(), "nil", 0);
        String flag = ticketService.insertT(t);
		return flag;
	}

	@PutMapping("/cancel")
	public String cancelTicket(@RequestBody User user) {
		String flag = null;
		flag = ticketService.cancelTicketBooked(user.getId(), user.getShowId());
		return flag;
	}

}
