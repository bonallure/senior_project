package com.cliniconline.platform.model;

import javax.persistence.*;
import java.util.Date;
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
    private Date DOB;
    private int SSN;
    private final Role role = Role.DOCTOR;

    @OneToMany
    private Set<Patient> patients;

    @ManyToMany
    private Set<Prescription> prescriptions;

    @ManyToMany
    private Set<Appointment> appointments;

    @ManyToMany
    private Set<Message> messages;

    public Doctor() {
    }

    public Doctor(Long id, String firstName, String lastName, String email, Date DOB, int SSN) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.DOB = DOB;
        this.SSN = SSN;
    }

    @Override
    public void changePassword(String password) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public int getPhoneNumber() {
        return 0;
    }

    @Override
    public void setPhoneNumber(int number) {

    }

    @Override
    public int getSSN() {
        return 0;
    }

    @Override
    public void setSSN(int ssn) {

    }

    @Override
    public Date getDOB() {
        return null;
    }

    @Override
    public void startCall(Call call) {

    }

    @Override
    public void joinCall(Call call) {

    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void replyToMessage(String response) {

    }

    @Override
    public Set<Message> viewMessages() {
        return new HashSet<>();
    }

    @Override
    public Set<Appointment> viewAllAppointments() {
        return new HashSet<>();
    }

    @Override
    public Set<Prescription> viewPrescriptions() {
        return null;
    }

    @Override
    public User viewAccount() {
        return null;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void setAddress(Address address) {

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
