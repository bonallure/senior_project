package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.AdultPatient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bonallure on 10/25/21
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    protected DoctorDao doctorDao;

    @Autowired
    protected AdultPatientDao adultPatientDao;
    @Autowired
    protected DependentDao dependentDao;
    @Autowired
    protected MessageDao messageDao;
    @Autowired
    protected AppointmentDao appointmentDao;
    @Autowired
    protected PrescriptionDao prescriptionDao;

    // wiring in the MockMvc objects
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();


    // Testing GET /patient/login
    @Test
    public void login() throws Exception {
        // ARRANGE
        AdultPatient patient = new AdultPatient();
        String email = "patient@clinic1.com";
        String password = "New/Password";
        patient.setEmail(email);
        patient.setPassword(password);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(patient);

        // ACT
        mockMvc.perform(
                get("/patient/login")                   // Perform the POST request
                        .content(inputJson)                       // Set the request body
                        .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
        )
                .andDo(print())                                // Print results to console
                .andExpect(status().isAccepted());              // ASSERT ( proper status code)
    }
}