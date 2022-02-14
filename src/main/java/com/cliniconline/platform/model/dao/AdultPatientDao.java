package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Dependent;
import com.cliniconline.platform.model.dto.UserAuthority;

import java.util.List;

/**
 * Created by bonallure on 10/14/21
 */

public interface AdultPatientDao {

    AdultPatient addAdultPatient(AdultPatient adultPatient);

    void updateAdultPatient(AdultPatient adultPatient);

    void deleteAdultPatient(String email);

    AdultPatient getPatient(String email);

    AdultPatient getPatientById(int id);

    AdultPatient getGuardian(Dependent dependent);

    List<AdultPatient> getAllAdultPatient();

    List<AdultPatient> getAllAdultPatientByDoctor(int doctorId);
}
