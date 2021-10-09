package com.cliniconline.platform.model;

/**
 * Created by bonallure on 10/8/21
 */
public interface Admin {

    void addPatient(Patient patient);

    void addDoctor(Doctor doctor);

    void addPharmacy(Pharmacy pharmacy);

    void graduatePatient();

    Long getId();

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail();

    void changePassword(String password);

    Role getAuthority();

    @Override
    String toString();
}
