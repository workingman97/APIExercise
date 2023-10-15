package com.exercise.ApiExercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exercise.ApiExercise.entity.Alert;


@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
	
	
	// query to retrieve alerts based on service ID and a time range
	@Query(value = "SELECT * FROM alerts WHERE service_id = :serviceId AND alert_ts BETWEEN :startTs AND :endTs", nativeQuery = true)
    List<Alert> findByServiceIdAndAlertTsBetween(@Param("serviceId") String serviceId, @Param("startTs") long startTs, @Param("endTs") long endTs);
}
