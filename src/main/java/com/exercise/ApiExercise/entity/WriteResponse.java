package com.exercise.ApiExercise.entity;


/**
 * 
 * response body for POST request
 *
 */
public class WriteResponse {
	private String alertId;
    private String error;
	
	public WriteResponse() {
        // Default constructor
    }
	public WriteResponse(String alertId, String error) {

		this.alertId = alertId;
		this.error = error;
	}
	public String getAlertId() {
		return alertId;
	}
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
