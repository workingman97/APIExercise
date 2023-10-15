package com.exercise.ApiExercise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exercise.ApiExercise.entity.Alert;

@Service
public interface AlertService {
	
	Alert saveAlert(Alert alert);
	List<Alert> getAlerts(String serviceId, long startTs, long endTs);
}
