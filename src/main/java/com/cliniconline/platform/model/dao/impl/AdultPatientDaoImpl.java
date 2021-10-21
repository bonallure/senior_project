package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bonallure on 10/19/21
 */
@Repository
public class AdultPatientDaoImpl implements AdultPatientDao {

    // Prepare statements
    private static final String INSERT_ADULT_PATIEN_SQL =
            "insert in adult_patient (id, email, f_name, l_name, password, address, phone, dob, ssn, role, messages," +
                    "appointments, dependents, doctor_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ADULT_PATIENT_SQL =
            "select * from adult_patient where id = ?";

    private static final String SELECT_ALL_ADULT_PATIENTS_SQL =
            "select * from adult_patient";

    private static final String DELETE_ADULT_PATIENT_SQL =
            "delete from adult_patient where id = ?";

    private static final String UPDATE_ADULT_PATIENT_SQL =
            "update adult_patient set id = ?, email = ?, f_name = ?, l_name = ?, password = ?, address = ?, phone = ?," +
                    "dob = ?, ssn = ?, role = ?, messages = ?, appointments = ?, dependents = ?, doctor_id = ?," +
                    "where id = ?";

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
