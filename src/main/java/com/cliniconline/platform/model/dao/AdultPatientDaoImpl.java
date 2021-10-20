package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bonallure on 10/19/21
 */
@Repository
public class AdultPatientDaoImpl implements AdultPatientDAO {

    @Override
    public AdultPatient getPatient(String email) {
        return null;
    }

    @Override
    public List<AdultPatient> getAllAdultPatient() {
        return null;
    }

    @Override
    public AdultPatient addAdultPatient(AdultPatient adultPatient) {
        return null;
    }

    @Override
    public void updateAdultPatient(AdultPatient adultPatient) {

    }

    @Override
    public void deleteAdultPatient(String email) {

    }

    @Override
    public List<AdultPatient> getAllAdultPatientByDoctor(Doctor doctor) {
        return null;
    }
}
