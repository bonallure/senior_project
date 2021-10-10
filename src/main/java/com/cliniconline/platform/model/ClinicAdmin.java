package com.cliniconline.platform.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class ClinicAdmin implements Admin{

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @Id
    private String email;
    private String password;
    private final Role role = Role.CLINIC_ADMIN;

    public ClinicAdmin() {

    }

    public ClinicAdmin(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public void addPatient(AdultPatient adultPatient) {

    }

    @Override
    public void addDoctor(Doctor doctor) {

    }

    @Override
    public void addPharmacy(Pharmacy pharmacy) {

    }

    @Override
    public void graduatePatient() {

    }

    public Long getId() {
        return id;
    }

    @Override
    public void addDependent(Dependent dependent, AdultPatient gardian, Doctor doctor) {

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

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
