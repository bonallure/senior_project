package com.cliniconline.platform.model.dto;

/**
 * Created by bonallure on 10/8/21
 */

public class ClinicAdmin implements Admin{

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int password;
    private final Role role = Role.CLINIC_ADMIN;

    public ClinicAdmin() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getRole() {
        return role.toString();
    }

    public int getId() {
        return id;
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
        return this.email;
    }

    @Override
    public void changePassword(int password) {
        this.password = password;
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
    public void setRole(String role) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClinicAdmin)) return false;

        ClinicAdmin that = (ClinicAdmin) o;

        if (id != that.id) return false;
        if (password != that.password) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!email.equals(that.email)) return false;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password;
        result = 31 * result + role.hashCode();
        return result;
    }
}
