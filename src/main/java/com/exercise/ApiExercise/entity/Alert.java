package com.exercise.ApiExercise.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entity representing an alert in the system.
 * Alerts contain information about events that require attention or action.
 */
@Entity
@Table(name = "alerts")
public class Alert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "alert_id")
	private String alert_id;
	
	@Column(name = "service_id")
    private String service_id;
	
	@Column(name = "service_name")
    private String service_name;
	
	@Column(name = "model")
    private String model;

	@Column(name = "alert_type")
    private String alert_type;
	
	@Column(name = "alert_ts")
    private Long alert_ts;
	
	@Column(name = "severity")
    private String severity;
	
	@Column(name = "team_slack")
    private String team_slack;
	
	
	// Getters and setters
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

	public Long getAlert_ts() {
		return alert_ts;
	}

	public void setAlert_ts(String alert_ts) {
		this.alert_ts = Long.parseLong(alert_ts);
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
