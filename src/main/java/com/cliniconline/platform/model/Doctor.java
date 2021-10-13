package com.cliniconline.platform.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class Doctor implements User{

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Object address;
    private int phoneNumber;
    private Calendar DOB;
    private final Role role = Role.DOCTOR;

    @OneToMany
    private Set<AdultPatient> adultPatients;

    @ManyToMany
    private Set<Prescription> prescriptions;

    @ManyToMany
    private Set<Appointment> appointments;

    @ManyToMany
    private Set<Message> messages;

    public Doctor() {
    }

    public Doctor(Long id, String firstName, String lastName, String email, Calendar DOB) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.DOB = DOB;
        messages = new HashSet<>();
        appointments = new HashSet<>();
        prescriptions = new HashSet<>();
        adultPatients = new HashSet<>();
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

    @Override
    public Calendar getDOB() {
        return DOB;
    }

    @Override
    public void startCall(Call call) {
        //TODO
    }

    @Override
    public void joinCall(Call call) {
        //TODO
    }

    @Override
    public void sendMessage(String message) {
        //TODO
    }

    @Override
    public void replyToMessage(String response) {
        //TODO
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

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
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
        return (Address) address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (id != null ? !id.equals(doctor.id) : doctor.id != null) return false;
        if (firstName != null ? !firstName.equals(doctor.firstName) : doctor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(doctor.lastName) : doctor.lastName != null) return false;
        if (email != null ? !email.equals(doctor.email) : doctor.email != null) return false;
        if (password != null ? !password.equals(doctor.password) : doctor.password != null) return false;
        return role == doctor.role;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + role.hashCode();
        return result;
    }
}
