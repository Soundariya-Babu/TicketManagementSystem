package com.project.model;

import java.time.LocalTime;

public class TheatreScreenShows {
	private int id;
	private LocalTime startTime;
	private LocalTime endTime;
	private TheatreScreen theatreScreen;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TheatreScreen getTheatreScreen() {
		return theatreScreen;
	}

	public void setTheatreScreen(TheatreScreen theatreScreen) {
		this.theatreScreen = theatreScreen;
	}

	public TheatreScreenShows(LocalTime startTime, LocalTime endTime, TheatreScreen theatreScreen) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.theatreScreen = theatreScreen;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public TheatreScreenShows() {
		// TODO Auto-generated constructor stub
	}

}
