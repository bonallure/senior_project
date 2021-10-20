package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dto.Appointment;
import com.cliniconline.platform.model.dto.Message;
import com.cliniconline.platform.model.dto.User;

import java.util.Set;

/**
 * Created by bonallure on 10/14/21
 */
public interface UserAPI {

    User viewAccount();

    Set<Long> getDependents();

    void setDependents(Set<Long> dependents);

    void sendMessage(String message);

    void viewMessage(String response);

    Set<Message> viewInbox();

    Set<Message> viewOutbox();

    Set<Long> viewAppointments();

    Set<Long> viewAppointment();

    void joinAppointment(Appointment appointment);

    void endAppointment(Appointment appointment);

    void addAppointment(Long appointmentId);

    void cancelAppointment(Long appointmentId);

    Set<Long> viewPrescriptions();

    Set<Long> viewPrescription();

    void writePrescription(Long prescriptionId);
}
