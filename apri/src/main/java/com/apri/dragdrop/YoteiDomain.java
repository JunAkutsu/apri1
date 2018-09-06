package com.apri.dragdrop;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YoteiDomain {

	private int action_type;
	private Long id;
	private Long resourceId;
	private String event_name;
	private LocalDateTime event_day_from;
	private String event_time_from;
	private LocalDateTime event_day_to;
	private String event_time_to;
	private String detail_naiyou;
	
	private String event_day_from_s;
	private String event_day_to_s;
	
	
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public String getEvent_day_from_s() {
		return event_day_from_s;
	}
	public void setEvent_day_from_s(String event_day_from_s) {
		this.event_day_from_s = event_day_from_s;
	}
	public String getEvent_day_to_s() {
		return event_day_to_s;
	}
	public void setEvent_day_to_s(String event_day_to_s) {
		this.event_day_to_s = event_day_to_s;
	}
	public int getAction_type() {
		return action_type;
	}
	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public LocalDateTime getEvent_day_from() {
		return event_day_from;
	}
	public void setEvent_day_from(LocalDateTime event_day_from) {
		this.event_day_from = event_day_from;
	}
	public String getEvent_time_from() {
		return event_time_from;
	}
	public void setEvent_time_from(String event_time_from) {
		this.event_time_from = event_time_from;
	}
	public LocalDateTime getEvent_day_to() {
		return event_day_to;
	}
	public void setEvent_day_to(LocalDateTime event_day_to) {
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
