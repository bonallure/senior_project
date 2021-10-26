package com.cliniconline.platform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bonallure on 10/25/21
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    // wiring in the MockMvc objects
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();


    // Testing GET /patient/login
    @Test
    public void login() throws Exception {
        // Arrange

        //ACT
        mockMvc.perform(get("/patient/login/?email=patient@clinic1.com&password=Mal.Com.Ex"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void viewAccount() {
    }

    @Test
    public void getDependents() {
    }

    @Test
    public void sendMessage() {
    }

    @Test
    public void viewMessage() {
    }

    @Test
    public void viewInbox() {
    }

    @Test
    public void viewOutbox() {
    }

    @Test
    public void viewAppointments() {
    }

    @Test
    public void viewAppointment() {
    }

    @Test
    public void joinAppointment() {
    }

    @Test
    public void endAppointment() {
    }

    @Test
    public void cancelAppointment() {
    }

    @Test
    public void viewPrescriptions() {
    }

    @Test
    public void viewPrescription() {
    }
}