package com.project.model;

public class TheatreScreen {
	private int id;
	private String name;
	private int totalTickets;

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

	public int getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}

	public TheatreScreen(int id, String name, int totalTickets) {
		super();
		this.id = id;
		this.name = name;
		this.totalTickets = totalTickets;
	}

	public TheatreScreen() {
		// TODO Auto-generated constructor stub
	}
}
