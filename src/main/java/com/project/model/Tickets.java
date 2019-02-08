package com.project.model;

import java.time.LocalDate;

public class Tickets {
private int id;
private int noOfSeats;
private TheatreScreenShows theatreScreenShows;
private LocalDate date;
private String status;
private int totalCost;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getNoOfSeats() {
	return noOfSeats;
}
public void setNoOfSeats(int noOfSeats) {
	this.noOfSeats = noOfSeats;
}
public TheatreScreenShows getTheatreScreenShows() {
	return theatreScreenShows;
}
public void setTheatreScreenShows(TheatreScreenShows theatreScreenShows) {
	this.theatreScreenShows = theatreScreenShows;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getTotalCost() {
	return totalCost;
}
public void setTotalCost(int totalCost) {
	this.totalCost = totalCost;
}

public Tickets() {
	// TODO Auto-generated constructor stub
}
public Tickets(int noOfSeats, TheatreScreenShows theatreScreenShows, LocalDate date, String status, int totalCost) {
	super();
	this.noOfSeats = noOfSeats;
	this.theatreScreenShows = theatreScreenShows;
	this.date = date;
	this.status = status;
	this.totalCost = totalCost;
}


}
