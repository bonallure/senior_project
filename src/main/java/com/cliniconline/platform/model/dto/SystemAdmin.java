package com.cliniconline.platform.model.dto;

import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dao.DependentDao;
import com.cliniconline.platform.model.dao.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by bonallure on 10/8/21
 */

public class SystemAdmin implements Admin{

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private int password;
    private Role role = Role.SYSTEM_ADMIN;

    public SystemAdmin() {
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getPassword() {
        return password;
    }

    @Override
    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String getRole() {
        return role.toString();
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void changePassword(int password) {
        this.password = password;
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
    public void setRole(String role) {
        this.role = Role.getRole(role.toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemAdmin)) return false;

        SystemAdmin that = (SystemAdmin) o;

        if (id != that.id) return false;
        if (password != that.password) return false;
        if (!email.equals(that.email)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + password;
        result = 31 * result + role.hashCode();
        return result;
    }
}
