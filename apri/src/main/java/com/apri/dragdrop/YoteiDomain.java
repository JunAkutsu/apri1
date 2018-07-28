package com.apri.dragdrop;

import java.time.LocalDate;

public class YoteiDomain {

	String event_name;
	LocalDate event_day_from;
	String event_time_from;
	LocalDate event_day_to;
	String event_time_to;
	String detail_naiyou;
	
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public LocalDate getEvent_day_from() {
		return event_day_from;
	}
	public void setEvent_day_from(LocalDate event_day_from) {
		this.event_day_from = event_day_from;
	}
	public String getEvent_time_from() {
		return event_time_from;
	}
	public void setEvent_time_from(String event_time_from) {
		this.event_time_from = event_time_from;
	}
	public LocalDate getEvent_day_to() {
		return event_day_to;
	}
	public void setEvent_day_to(LocalDate event_day_to) {
		this.event_day_to = event_day_to;
	}
	public String getEvent_time_to() {
		return event_time_to;
	}
	public void setEvent_time_to(String event_time_to) {
		this.event_time_to = event_time_to;
	}
	public String getDetail_naiyou() {
		return detail_naiyou;
	}
	public void setDetail_naiyou(String detail_naiyou) {
		this.detail_naiyou = detail_naiyou;
	}
	
	
}
