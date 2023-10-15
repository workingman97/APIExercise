package com.exercise.ApiExercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.ApiExercise.entity.Alert;
import com.exercise.ApiExercise.repository.AlertRepository;


@Service
public class AlertServiceImpl implements AlertService {
	
	@Autowired
	private AlertRepository alertRepository;
	
	
	// Save the alert using the alert repository
	@Override 
	public Alert saveAlert(Alert alert) {
		return alertRepository.save(alert);
	}
	
	
	// Retrieve alerts based on service ID and a time range using the alert repository
	@Override
	public List<Alert> getAlerts(String serviceId, long startTs, long endTs) {
		
		return alertRepository.findByServiceIdAndAlertTsBetween(serviceId, startTs, endTs);
	}
	
}
