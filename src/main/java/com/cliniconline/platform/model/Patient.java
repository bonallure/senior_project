package com.cliniconline.platform.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/10/21
 */
public abstract class Patient implements User{

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Address address;
    private int phoneNumber;
    private Calendar DOB;
    private int SSN;
    private final Role role = Role.PATIENT;

    @ManyToOne
    private Doctor doctor;

    @OneToMany
    private Set<Prescription> prescriptions;
    @OneToMany
    private Set<Appointment> appointments;
    @OneToMany
    private Set<Message> messages;

    public Patient() {
    }

    public Patient(String email, String firstName, String lastName, String password, Address address,
                        int phoneNumber, Calendar DOB, int SSN, Doctor doctor) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
        this.SSN = SSN;
        this.doctor = doctor;
        messages = new HashSet<>();
        appointments = new HashSet<>();
        prescriptions = new HashSet<>();
    }

    @Override
    public void startCall(Call call) {
        // TODO
    }

    @Override
    public void joinCall(Call call) {
        // TODO
    }

    @Override
    public void sendMessage(String message) {
        // TODO
    }

    @Override
    public void replyToMessage(String response) {
        // TODO
    }

    @Override
    public Set<Message> viewMessages() {
        return messages;
    }

    @Override
    public Set<Appointment> viewAllAppointments() {
        return appointments;
    }

    @Override
    public void addApointment(Appointment appointment) {
        appointments.add(appointment);
    }

    @Override
    public Set<Prescription> viewPrescriptions() {
        return prescriptions;
    }

    @Override
    public User viewAccount() {
        return this;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public void changePassword(String password) {
        this.password = password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(int number) {
        phoneNumber = number;
    }

    public int getSSN() {
        return SSN;
    }

    @Override
    public Calendar getDOB() {
        return DOB;
    }
}
