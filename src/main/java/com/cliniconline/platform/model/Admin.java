package com.cliniconline.platform.model;

import java.util.Date;

/**
 * Created by bonallure on 10/8/21
 */
public interface Admin {

    void addPatient(AdultPatient adultPatient);

    void addDoctor(Doctor doctor);

    void addPharmacy(Pharmacy pharmacy);

    void graduatePatient();

    Long getId();

    void addDependent(Dependent dependent, AdultPatient gardian, Doctor doctor);

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
