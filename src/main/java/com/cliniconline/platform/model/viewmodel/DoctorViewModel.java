package com.cliniconline.platform.model.viewmodel;

import com.cliniconline.platform.model.dto.*;

import java.util.List;

/**
 * Created by bonallure on 11/29/21
 */
public class DoctorViewModel implements UserViewModel{
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private List<Appointment> appointments;
    private List<Message> outbox;
    private List<Message> inbox;
    private List<Patient> patients;
    private List<Prescription> prescriptions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Message> getOutbox() {
        return outbox;
    }

    public void setOutbox(List<Message> messages) {
        this.outbox = messages;
    }

    public List<Message> getIntbox() {
        return inbox;
    }

    public void setIntbox(List<Message> messages) {
        this.inbox = messages;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
