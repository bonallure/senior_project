package com.cliniconline.platform.model.dto;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Calendar;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/10/21
 */
public abstract class Patient implements User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private int phoneNumber;
    private Date DOB;
    private int SSN;
    protected Role role;
    @OneToMany
    @JoinColumn(name = "MESSAGE_ID")
    private Set<Message> messages = new HashSet<>();
    @OneToMany
    @JoinColumn(name = "APPOINTMENT_ID")
    private Set<Appointment> appointments = new HashSet<>();
    @ManyToOne
    private Doctor doctor;

    public Patient() {
    }

    public Patient(String email, String firstName, String lastName, String password, String address,
                   int phoneNumber, Date DOB, int SSN, Role role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
        this.SSN = SSN;
        this.role = role;
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
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
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
    public Date getDOB() {
        return DOB;
    }

    @Override
    public Set<Message> getMessages() {
        return messages;
    }

    @Override
    public void setMessages(Set<Message> messages) {
        this.messages.addAll(messages);
    }

    @Override
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments.addAll(appointments);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public boolean isAdult(){
        Calendar ageOfMaturity = (Calendar) getDOB().clone();
        ageOfMaturity.add(Calendar.YEAR, -18);

        return ageOfMaturity.before(Calendar.getInstance());
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
