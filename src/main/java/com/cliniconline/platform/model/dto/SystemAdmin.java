package com.cliniconline.platform.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by bonallure on 10/8/21
 */

public class SystemAdmin implements Admin{

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private final Role role = Role.SYSTEM_ADMIN;

    public SystemAdmin() {
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void addDependent(Dependent dependent, AdultPatient guardian, Doctor doctor) {

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
    public String getEmail() {
        return null;
    }

    @Override
    public void setEmail() {

    }

    @Override
    public void changePassword(String password) {

    }

    @Override
    public Role getAuthority() {
        return null;
    }

    @Override
    public String toString() {
        return "System Admin{" +
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

        SystemAdmin that = (SystemAdmin) o;

        if (id != that.id) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!password.equals(that.password)) return false;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
