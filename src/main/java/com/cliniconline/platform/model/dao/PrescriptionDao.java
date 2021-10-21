package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.Prescription;

import java.util.List;

/**
 * Created by bonallure on 10/14/21
 */

public interface PrescriptionDao {

    Prescription addPrescription(Prescription prescription);

    void updatePrescription(Prescription prescription);

    void deletePrescription(int prescriptionId);

    Prescription getPrescription(int prescriptionId);

    List<Prescription> getAllPrescription();

    List<Prescription> getAllPrescriptionsPerPatient(int patientId);

    List<Prescription> getAllPrescriptionsPerDoctor(int doctorId);

}
