package com.cliniconline.platform.model.dto;

import java.sql.Date;

/**
 * Created by bonallure on 10/10/21
 */
public abstract class Patient implements User{

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private int phoneNumber;
    private Date DOB;
    private int SSN;
    protected Role role;
    private int doctorId;

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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    public String getRole() {
        return role.toString();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;

        Patient patient = (Patient) o;

        if (id != patient.id) return false;
        if (phoneNumber != patient.phoneNumber) return false;
        if (SSN != patient.SSN) return false;
        if (doctorId != patient.doctorId) return false;
        if (!email.equals(patient.email)) return false;
        if (!firstName.equals(patient.firstName)) return false;
        if (!lastName.equals(patient.lastName)) return false;
        if (!password.equals(patient.password)) return false;
        if (!address.equals(patient.address)) return false;
        if (!DOB.equals(patient.DOB)) return false;
        return role == patient.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phoneNumber;
        result = 31 * result + DOB.hashCode();
        result = 31 * result + SSN;
        result = 31 * result + role.hashCode();
        result = 31 * result + doctorId;
        return result;
    }
}
