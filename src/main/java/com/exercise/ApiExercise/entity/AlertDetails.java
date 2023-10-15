package com.exercise.ApiExercise.entity;


/**
 * 
 * Alert details that retrieve by the GET request
 *
 */
public class AlertDetails {
	private String alert_id;
	private String model;
	private String alert_type;
	private String alert_ts;
	private String severity;
	private String team_slack;

	// Getters and setters
	public String getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(String alert_id) {
		this.alert_id = alert_id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAlert_type() {
		return alert_type;
	}

	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}

	public String getAlert_ts() {
		return alert_ts;
	}

	public void setAlert_ts(String alert_ts) {
		this.alert_ts = alert_ts;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getTeam_slack() {
		return team_slack;
	}

	public void setTeam_slack(String team_slack) {
		this.team_slack = team_slack;
	}
}
