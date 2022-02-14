package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.model.viewmodel.PatientViewModel;
import com.cliniconline.platform.model.viewmodel.UserViewModel;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Set;

/**
 * Created by bonallure on 10/14/21
 */
public interface UserControllers {

    ResponseEntity<?> login(String email);

    User viewAccount(String email);

    // Set<Dependent> getDependents(int adultPatientID);

    // void addDependent(Dependent dependent);

    Message sendMessage(Message message);

    Message viewMessage(int messageId);

    Set<Message> viewInbox(int userId);

    Set<Message> viewOutbox(int userId);

    Set<Appointment> viewAppointments(int userID);

    Appointment viewAppointment(int appointmentId);

    void joinAppointment(int appointment, int user_id);

    void endAppointment(int appointmentId);

    Appointment addAppointment(Map appointment);

    void cancelAppointment(int appointmentId);

    Set<Prescription> viewPrescriptions(int userId);

    Prescription viewPrescription(int prescriptionId);

    // void writePrescription(Long prescriptionId);
}