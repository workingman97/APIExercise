package com.exercise.ApiExercise.entity;



/**
 * 
 * POST request body
 *
 */
public class WriteRequest {

    private String alert_id;

    private String service_id;

    private String service_name;

    private String model;

    private String alert_type;

    private String alert_ts;

    private String severity;

    private String team_slack;

	public String getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(String alert_id) {
		this.alert_id = alert_id;
	}

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
    
    
 // Getters and setters
    
    



    
}