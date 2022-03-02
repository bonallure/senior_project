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

    ResponseEntity<?> viewAccount(String email);

    // Set<Dependent> getDependents(int adultPatientID);

    // void addDependent(Dependent dependent);

    ResponseEntity<?> sendMessage(Message message);

    ResponseEntity<?> viewMessage(int messageId);

    ResponseEntity<?> viewInbox(int userId);

    ResponseEntity<?> viewOutbox(int userId);

    ResponseEntity<?> viewAppointments(int userID);

    ResponseEntity<?> viewAppointment(int appointmentId);

    void joinAppointment(int appointment, int user_id);

    void endAppointment(int appointmentId);

    ResponseEntity<?> addAppointment(Appointment appointment);

    void cancelAppointment(int appointmentId);

    ResponseEntity<?> viewPrescriptions(int userId);

    ResponseEntity<?> viewPrescription(int prescriptionId);

    // void writePrescription(Long prescriptionId);
}
