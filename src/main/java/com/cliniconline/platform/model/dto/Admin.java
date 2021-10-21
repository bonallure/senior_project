package com.cliniconline.platform.model.dto;

/**
 * Created by bonallure on 10/8/21
 */
public interface Admin {

    AdultPatient addPatient(AdultPatient adultPatient);

    Doctor addDoctor(Doctor doctor);

    AdultPatient graduatePatient(String email, Dependent dependent);

    void setId(int id);

    int getId();

    Dependent addDependent(Dependent dependent);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    void changePassword(String password);

    @Override
    String toString();

    void setRole(String role);

    String getRole();
}
