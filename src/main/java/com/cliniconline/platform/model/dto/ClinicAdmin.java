package com.cliniconline.platform.model.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by bonallure on 10/8/21
 */

public class ClinicAdmin implements Admin{

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private final Role role = Role.CLINIC_ADMIN;

    public ClinicAdmin() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public void addPatient(AdultPatient adultPatient) {

    }

    @Override
    public void addDoctor(Doctor doctor) {

    }

    @Override
    public void graduatePatient() {

    }

    public int getId() {
        return id;
    }

    @Override
    public void addDependent(Dependent dependent, AdultPatient guardian, Doctor doctor) {

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

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public void setEmail() {

    }

    @Override
    public void changePassword(String password) {

    }

    public Role getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return "Clinic Admin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", authority=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClinicAdmin that = (ClinicAdmin) o;

        if (id != that.id) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!email.equals(that.email)) return false;
        if (!password.equals(that.password)) return false;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
