package com.exercise.ApiExercise.entity;

import java.util.List;

/**
 *
 * Success response for GET request 
 *
 */

public class ReadResponse {
	private String service_id;
    private String service_name;
    private List<AlertDetails> alerts;
    
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public List<AlertDetails> getAlerts() {
		return alerts;
	}
	public void setAlerts(List<AlertDetails> alerts) {
		this.alerts = alerts;
	}
}
