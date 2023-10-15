package com.exercise.ApiExercise.entity;


/**
 * 
 * Failed message for GET request
 *
 */
public class ReadFailResponse {
	private Boolean success;
	private String alertId;
    private String error;
	
	public ReadFailResponse() {
        // Default constructor
    }
	public ReadFailResponse(Boolean success, String alertId, String error) {
		this.success = success;
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
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
