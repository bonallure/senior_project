package com.cliniconline.platform.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class SystemAdmin implements Admin{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private final Role role = Role.SYSTEM_ADMIN;

    public SystemAdmin() {

    }

    public SystemAdmin(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void addDependent(Dependent dependent, AdultPatient gardian, Doctor doctor) {

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

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
