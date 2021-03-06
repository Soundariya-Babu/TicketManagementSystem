package com.project.service;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.dao.TicketDao;
import com.project.model.TheatreScreenShows;
import com.project.model.Tickets;
import com.project.model.User;
import com.project.service.exception.ServiceLayerException;
import com.project.service.exception.SuccessException;

@Service
public class TicketServiceImple implements TicketService {
	@Autowired
	private TicketDao ticketDao;

	@Override
	public void insertT(User user) throws ServiceLayerException, SuccessException {
		TheatreScreenShows tss = new TheatreScreenShows();
		tss.setId(user.getShowId());
		Tickets t = new Tickets(user.getNoOfSeats(), tss, user.getDate(), "nil", 0);

		String flag = "InternalServiceError";
		LocalDate lc = LocalDate.now();
		LocalTime lt = LocalTime.now();
		LocalTime lt1 = ticketDao.selectStartTime(t.getTheatreScreenShows().getId());
		if (lt.isAfter(lt1)) {
			flag = "Sorry! The specified show is already started";
			throw new ServiceLayerException(flag);
		}

		if (t.getNoOfSeats() > 10) {
			flag = "You can book only 10 tickets ";
			throw new ServiceLayerException(flag);
		}
		if (!lc.equals(t.getDate())) {
			flag = "Enter the current date";
			throw new ServiceLayerException(flag);
		}
		int seats = 0;
		seats = ticketDao.selectSeats(t.getTheatreScreenShows().getId(), lc);
		int ticketCount = ticketDao.selectTotalSeat(t.getTheatreScreenShows().getId());
		int filling_seats = seats + t.getNoOfSeats();

		if (filling_seats > ticketCount) {
			int ticketsAvailable;
			ticketsAvailable = ticketCount - seats;
			if (ticketsAvailable < 1) {
				flag = "Tickest not available for the selected show";
			} else {
				if (ticketsAvailable == 1) {
					flag = "Only one ticket is available for the selected show";
					throw new ServiceLayerException(flag);
				} else {
					flag = "Only " + ticketsAvailable + " tickets are available for the selected show";
					throw new ServiceLayerException(flag);
				}

			}
			throw new ServiceLayerException(flag);
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
		throw new SuccessException(flag);
	}

	public void cancelTicketBooked(int id, int showId) throws ServiceLayerException, SuccessException {
		String flag = "InternalServiceError";
		LocalTime lt = LocalTime.now();
		LocalDate ld = LocalDate.now();
		LocalDate ld1 = ticketDao.getDate(id);

		if (ld.equals(ld1)) {
			LocalTime lt1 = ticketDao.selectStartTime(showId);
			if (lt.isAfter(lt1)) {
				flag = "The show already started you are not allowed to cancel the tickets";
				throw new ServiceLayerException(flag);
			}

			else if (lt.isBefore(lt1.minusMinutes(10))) {
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
					flag = "Your tickets has been cancelled successfully";
					throw new SuccessException(flag);
				}
				
			} else {
				flag = "Sorry, you cannot cancel this ticket, as the show is going to start in 10 mins";
				throw new ServiceLayerException(flag);
			}

		} else {
			flag = "The specified show is over";
			throw new ServiceLayerException(flag);
		}

		throw new ServiceLayerException(flag);
	}

}
