package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.PrescriptionDao;
import com.cliniconline.platform.model.dto.Prescription;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bonallure on 10/20/21
 */
@Repository
public class PrescriptionDaoImpl implements PrescriptionDao {

    private static final String INSERT_PRESCRIPTION_SQL =
            "insert in prescription (id, doctor_id, patient_id, date, medicine, dosage, quantity, refillable) values" +
                    " (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_PRESCRIPTIONS_SQL =
            "select * from prescription where id = ?";

    private static final String SELECT_ALL_PRESCRIPTIONS_SQL =
            "select * from prescription";

    private static final String DELETE_PRESCRIPTION_SQL =
            "delete from prescription where id = ?";

    private static final String UPDATE_PRESCRIPTION_SQL =
            "update prescription set id = ?, doctor_id = ?, patient_id = ?, date = ?, medicine = ?, dosage = ?," +
                    " quantity = ?, refillable = ?, where id = ?";

    @Override
    public Prescription addPrescription(Prescription prescription) {
        return null;
    }

    @Override
    public void updatePrescription(Prescription prescription) {

    }

    @Override
    public void deletePrescription(int prescriptionId) {

    }

    @Override
    public Prescription getPrescription(int prescriptionId) {
        return null;
    }

    @Override
    public List<Prescription> getAllPrescriptionsPerPatient(int patientId) {
        return null;
    }
}
