package com.cliniconline.platform.model.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
public class Doctor implements User{

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private int phoneNumber;
    private Date DOB;
    private final Role role = Role.DOCTOR;

    private Set<Message> messages = new HashSet<>();
    private Set<Appointment> appointments = new HashSet<>();
    private Set<Patient> patients = new HashSet<>();

    public Doctor() {
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

    public String getPassword() {
        return password;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients.addAll(patients);
    }

    @Override
    public void changePassword(String password) {
        setAddress(password);
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
    public Date getDOB() {
        return DOB;
    }

    @Override
    public void setRole(Role role) {

    }

    @Override
    public Role getRole() {
        return null;
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

    @Override
    public int getId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (id != doctor.id) return false;
        if (phoneNumber != doctor.phoneNumber) return false;
        if (!firstName.equals(doctor.firstName)) return false;
        if (!lastName.equals(doctor.lastName)) return false;
        if (!email.equals(doctor.email)) return false;
        if (!password.equals(doctor.password)) return false;
        if (!address.equals(doctor.address)) return false;
        if (!DOB.equals(doctor.DOB)) return false;
        if (role != doctor.role) return false;
        if (messages != null ? !messages.equals(doctor.messages) : doctor.messages != null) return false;
        if (appointments != null ? !appointments.equals(doctor.appointments) : doctor.appointments != null)
            return false;
        return patients != null ? patients.equals(doctor.patients) : doctor.patients == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phoneNumber;
        result = 31 * result + DOB.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        result = 31 * result + (appointments != null ? appointments.hashCode() : 0);
        result = 31 * result + (patients != null ? patients.hashCode() : 0);
        return result;
    }
}
