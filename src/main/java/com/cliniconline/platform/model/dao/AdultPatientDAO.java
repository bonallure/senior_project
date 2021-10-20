package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Doctor;
import com.cliniconline.platform.model.dto.Patient;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

/**
 * Created by bonallure on 10/14/21
 */
public interface AdultPatientDAO {

    AdultPatient getPatient(String email);

    List<AdultPatient> getAllAdultPatient();

    AdultPatient addAdultPatient(AdultPatient adultPatient);

    void updateAdultPatient(AdultPatient adultPatient);

    void deleteAdultPatient(String email);

    List<AdultPatient> getAllAdultPatientByDoctor(Doctor doctor);
}
