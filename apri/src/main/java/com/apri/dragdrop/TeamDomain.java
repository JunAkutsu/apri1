package com.apri.dragdrop;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TeamDomain {

	private Long id;
	private String title;
	private String eventColor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEventColor() {
		return eventColor;
	}
	public void setEventColor(String eventColor) {
		this.eventColor = eventColor;
	}
	
	
}
