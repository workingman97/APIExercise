package com.exercise.ApiExercise;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.exercise.ApiExercise.entity.WriteRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ApiExerciseApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	
	/**
     * Tests the writeAlert functionality of the API.
     * Sends a POST request to create a new alert and validates the response.
     *
     * @throws Exception if an error occurs during the test
     */
	@Test
    public void testWriteAlert() throws Exception {
        WriteRequest writeRequest = new WriteRequest();       
        writeRequest.setAlert_id("b950482e9911ec7e41f7ca5e5d9a424f");
        writeRequest.setService_id("my_test_service_id");
        writeRequest.setService_name("my_test_service");
        writeRequest.setModel("my_test_model");
        writeRequest.setAlert_ts("1695644160");
        writeRequest.setAlert_type("anomaly");
        writeRequest.setSeverity("warning");
        writeRequest.setTeam_slack("slack_ch");

        mockMvc.perform(post("/alerts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(writeRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alert_id").exists());
    }
	
	/**
     * Tests the readAlerts functionality of the API.
     * Sends a GET request to retrieve alerts and validates the response.
     *
     * @throws Exception if an error occurs during the test
     */
	@Test
    public void testReadAlerts() throws Exception {
        mockMvc.perform(get("/alerts")
                .param("service_id", "service_id")
                .param("start_ts", "1")
                .param("end_ts", "50"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.service_id").value("service_id"));
    }

}
