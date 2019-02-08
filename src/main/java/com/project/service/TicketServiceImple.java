package com.project.service;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.dao.TicketDao;
import com.project.model.Tickets;

@Service
public class TicketServiceImple implements TicketService {
	@Autowired
	private TicketDao ticketDao;

	public String insertT(Tickets t) {
		String flag = null;
		LocalDate lc = LocalDate.now();
		LocalTime lt = LocalTime.now();
		LocalTime lt1 = ticketDao.selectStartTime(t.getTheatreScreenShows().getId());
		if (lt.isAfter(lt1)) {
			flag = "Sorry! The specified show is already started";
			return flag;
		}

		if (t.getNoOfSeats() > 10) {
			flag = "You can book only 10 tickets ";
			return flag;
		}
		if (!lc.equals(t.getDate())) {
			flag = "Enter the current date";
			return flag;
		}
		int seats = 0;
		seats = ticketDao.selectSeats(t.getTheatreScreenShows().getId());
		int ticketCount = ticketDao.selectTotalSeat(t.getTheatreScreenShows().getId());
		int filling_seats = seats + t.getNoOfSeats();

		if (filling_seats > ticketCount) {
			int ticketsAvailable;
			ticketsAvailable = ticketCount - seats;
			if (ticketsAvailable < 1) {
				flag = "Tickest not available for the selected show";
			} else {
				flag = "only " + ticketsAvailable + " tickets are available for the selected show";
			}
			return flag;
		}
		String Status = "Confirmed";
		int cost = ticketDao.selectCost();
		int totalCost = t.getNoOfSeats() * cost;
		t.setStatus(Status);
		t.setTotalCost(totalCost);
		int t1 = ticketDao.insertTicket(t);
		if (t1 > 0) {
			int id = ticketDao.getTicketId();
			flag = "Your booking is confirmed." + "Your ticket id is " + id + " and total ticket cost is " + totalCost;
		}
		return flag;
	}

	public String cancelTicketBooked(int id, int showId) {
		String flag = "nil";
		LocalTime lt = LocalTime.now();
		LocalTime lt1 = ticketDao.selectStartTime(showId);
		if (lt.isAfter(lt1)) {
			flag = "The show already started you are not allowed to cancel the tickets";
			return flag;
		}
		LocalDate ld = LocalDate.now();
		LocalDate ld1 = ticketDao.getDate(id);
		if (ld.equals(ld1)) {
			if (lt.isBefore(lt1.minusMinutes(10))) {
				String status = "cancelled";
				int ticketcost = 0;
				int no_of_seats = 0;
				Tickets t = new Tickets();
				t.setId(id);
				t.setNoOfSeats(no_of_seats);
				t.setStatus(status);
				t.setTotalCost(ticketcost);
				int i = ticketDao.cancelTicket(t);
				if (i > 0) {
					flag = "your tickets has been cancelled successfully";
					return flag;
				} else {
					flag = "Sorry, you cannot cancel this ticket, as the show is going to start in 10 mins";
					return flag;
				}
			} else {
				flag = "The specified show is over";
				return flag;
			}
		}
		return flag;
	}

}
