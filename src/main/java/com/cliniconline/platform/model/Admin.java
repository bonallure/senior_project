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

    void setId(Long id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    Authority getAuthority();

    @Override
    String toString();
}
