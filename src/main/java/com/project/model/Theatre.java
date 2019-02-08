package com.project.model;

public class Theatre {
	private int id;
	private String name;
	private int noOfScreens;
	private int ticketCost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfScreens() {
		return noOfScreens;
	}

	public void setNoOfScreens(int noOfScreens) {
		this.noOfScreens = noOfScreens;
	}

	public int getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(int ticketCost) {
		this.ticketCost = ticketCost;
	}

	public Theatre(int id, String name, int noOfScreens, int ticketCost) {
		super();
		this.id = id;
		this.name = name;
		this.noOfScreens = noOfScreens;
		this.ticketCost = ticketCost;
	}

	public Theatre() {
		super();
	}

}
