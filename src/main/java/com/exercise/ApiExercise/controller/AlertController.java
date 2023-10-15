package com.exercise.ApiExercise.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.ApiExercise.entity.Alert;
import com.exercise.ApiExercise.entity.AlertDetails;
import com.exercise.ApiExercise.entity.ReadFailResponse;
import com.exercise.ApiExercise.entity.ReadResponse;
import com.exercise.ApiExercise.entity.WriteRequest;
import com.exercise.ApiExercise.entity.WriteResponse;
import com.exercise.ApiExercise.service.AlertService;

/**
 * The class handles HTTP requests related to alerts.
 * It provides endpoints for creating and retrieving alerts using a RESTful API.
 */
@RestController
public class AlertController {
	
	
	@Autowired
	private AlertService alertService;
	
	
	/**
     * Handles a POST request to create a new alert based on the provided write request.
     * Validates the request, maps it to an Alert object, and saves the alert.
     * Responds with a suitable WriteResponse based on the outcome.
     * @param writeRequest The write request containing alert data.
     * @return ResponseEntity containing the write response.
     */
	@PostMapping("/alerts")
	public ResponseEntity<WriteResponse> writeAlert(@RequestBody WriteRequest writeRequest) {
        try {
            validateRequest(writeRequest);
            
            // Map the write request to an Alert object
            Alert alert = mapToAlert(writeRequest);
            
            // Save the alert using the alert service
            Alert savedAlert = alertService.saveAlert(alert);
            
            // Respond with a success WriteResponse
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new WriteResponse(writeRequest.getAlert_id(), ""));
            
        } catch (ValidationException e) {
        	// Respond with a bad request WriteResponse for validation failure
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new WriteResponse(writeRequest.getAlert_id(), e.getMessage()));
            
        } catch (Exception e) {
        	// Respond with an internal server error WriteResponse for unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new WriteResponse(writeRequest.getAlert_id(), "Internal server error"));
        }
    }
	
	
	// Map the write request to an Alert object
	private Alert mapToAlert(WriteRequest writeRequest) {
        Alert alert = new Alert();
        alert.setAlert_id(writeRequest.getAlert_id());
        alert.setService_id(writeRequest.getService_id());
        alert.setService_name(writeRequest.getService_name());
        alert.setModel(writeRequest.getModel());
        alert.setAlert_type(writeRequest.getAlert_type());
        alert.setAlert_ts(writeRequest.getAlert_ts());
        alert.setSeverity(writeRequest.getSeverity());
        alert.setTeam_slack(writeRequest.getTeam_slack());
        return alert;
    }
	
	
	// Validate write request
	private void validateRequest(WriteRequest writeRequest) throws ValidationException {
	    if (writeRequest.getAlert_id() == null || writeRequest.getAlert_id().isEmpty()) {
	        throw new ValidationException("Alert ID is required and cannot be empty.");
	    }
	    if (writeRequest.getAlert_ts() == null || !isLong(writeRequest.getAlert_ts())) {
	        throw new ValidationException("Alert_ts is not a valid long value.");
	    }
	}

	private boolean isLong(String value) {
	    try {
	        Long.parseLong(value);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	
	/**
	 * Handles a GET request to retrieve alerts for a specific service within a given time range.
	 * @param serviceId The unique identifier for the service.
	 * @param startTs The start timestamp for the retrieval period.
	 * @param endTs The end timestamp for the retrieval period.
	 * @return ResponseEntity containing the read response or an appropriate error response.
	 */
	@GetMapping("/alerts")
    public ResponseEntity<?> readAlerts( 
    		@RequestParam("service_id") String serviceId, 
    		@RequestParam("start_ts") long startTs, 
    		@RequestParam("end_ts") long endTs) {
        try {
            validateTs(startTs, endTs);
            
        
            // Retrieve alerts for the specified service and time range
            List<Alert> alerts = alertService.getAlerts(serviceId, startTs, endTs);
            
            
            if (alerts.isEmpty()) {
            	// Respond with a not found ReadFailResponse if no alerts are available
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ReadFailResponse(false, serviceId,"No alerts available."));
                
            } else {
            	// Construct a ReadResponse with the retrieved alerts
                ReadResponse readResponse = constructReadResponse(alerts, serviceId);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(readResponse);
            }
        } catch (IllegalArgumentException e) {
        	
        	// Respond with a bad request ReadFailResponse for validation failure
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ReadFailResponse(false, serviceId, e.getMessage()));
        } catch (Exception e) {
        	
        	// Respond with an internal server error ReadFailResponse for unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ReadFailResponse(false, serviceId, "Internal server error."));
        }
    }
	
	
	// Validates the timestamps for correctness which start timestamp should be less than end timestamp.
    private void validateTs(long startTs, long endTs) {
        if (endTs < startTs) {
            throw new IllegalArgumentException("End timestamp is less than start timestamp.");
        }
    }
    
    
    // Constructs a ReadResponse based on the service identifier.
    private ReadResponse constructReadResponse(List<Alert> alerts, String serviceId) {
        ReadResponse readResponse = new ReadResponse();
        readResponse.setService_id(serviceId);
        readResponse.setService_name(alerts.get(0).getService_name());
        List<AlertDetails> alertDetails = new ArrayList<>();
        for (Alert alert : alerts) {
            AlertDetails alert1 = new AlertDetails();
            alert1.setAlert_id(alert.getAlert_id());
            alert1.setModel(alert.getModel());
            alert1.setAlert_type(alert.getAlert_type());
            alert1.setAlert_ts(alert.getAlert_ts().toString());
            alert1.setSeverity(alert.getSeverity());
            alert1.setTeam_slack(alert.getTeam_slack());
            alertDetails.add(alert1);
        }
        readResponse.setAlerts(alertDetails);
        return readResponse;
    }
}
