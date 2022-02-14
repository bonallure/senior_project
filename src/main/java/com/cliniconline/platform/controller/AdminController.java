package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dto.*;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bonallure on 10/31/21
 */
public interface AdminController {

    Admin login(Map admin);

    Admin viewAccount(int adminId);

    ResponseEntity<Doctor> addDoctor(Doctor doctor) throws Exception;

    AdultPatient addPatient(Map adultPatient);

    AdultPatient graduatePatient(int dependentId);

    Dependent addDependent(Map dependent);

    List<Doctor> getDoctors();

    List<Patient> getPatients();

    List<AdultPatient> getAdultPatients();

    List<Dependent> getDependents();
}
