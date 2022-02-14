package com.cliniconline.platform.model.dto;

import java.sql.Date;

/**
 * Created by bonallure on 10/8/21
 */
public class Doctor implements User{

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private Long phoneNumber;
    private Date DOB;
    private final Role role = Role.DOCTOR;

    public Doctor() {
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public UserAuthority getUserAuthority() {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUsername(this.email);
        userAuthority.setPassword(this.password);
        userAuthority.setRole(this.role);

        return userAuthority;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    @Override
    public void changePassword(String password) {
        setPassword(password);
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(Long number) {
        phoneNumber = number;
    }

    @Override
    public Date getDOB() {
        return DOB;
    }

    @Override
    public String getRole() {
        return role.toString();
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
        if (!(o instanceof Doctor)) return false;

        Doctor doctor = (Doctor) o;

        if (id != doctor.id) return false;
        if (!email.equals(doctor.email)) return false;
        if (!firstName.equals(doctor.firstName)) return false;
        if (!lastName.equals(doctor.lastName)) return false;
        if (!password.equals(doctor.password)) return false;
        if (!address.equals(doctor.address)) return false;
        if (!phoneNumber.equals(doctor.phoneNumber)) return false;
        if (!DOB.equals(doctor.DOB)) return false;
        return role == doctor.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + DOB.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
