package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dto.*;

import java.util.Map;

/**
 * Created by bonallure on 10/31/21
 */
public interface AdminController {

    Admin login(Map admin);

    Admin viewAccount(int adminId);

    Doctor addDoctor(Map doctor);

    AdultPatient addPatient(Map adultPatient);

    AdultPatient graduatePatient(int dependentId);

    Dependent addDependent(Map dependent);
}
