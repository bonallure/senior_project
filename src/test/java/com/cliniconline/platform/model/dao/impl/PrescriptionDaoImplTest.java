package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.dao.AdultPatientDao;
import com.cliniconline.platform.dao.DoctorDao;
import com.cliniconline.platform.dao.PrescriptionDao;
import com.cliniconline.platform.model.dto.Prescription;
import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Doctor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bonallure on 10/25/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PrescriptionDaoImplTest {

    @Autowired
    protected AdultPatientDao adultPatientDao;

    @Autowired
    protected DoctorDao doctorDao;

    @Autowired
    protected PrescriptionDao dao;

    @Before
    public void setUp() throws Exception {
        List<AdultPatient> patientList = adultPatientDao.getAllAdultPatient();
        for (AdultPatient patient : patientList) {
            adultPatientDao.deleteAdultPatient(patient.getEmail());
        }

        List<Doctor> doctorList = doctorDao.getAllDoctors();
        for (Doctor doctor : doctorList) {
            doctorDao.deleteDoctor(doctor.getId());
        }

        List<Prescription> prescriptionList = dao.getAllPrescription();
        for (Prescription prescription : prescriptionList) {
            dao.deletePrescription(prescription
                    .getId());
        }
    }

    @Test
    public void addGetDeletePrescription(){
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Grey");
        doctor.setEmail("jgrey@clinic1.com");
        doctor.setPassword("Doc.John.Grey".hashCode());
        doctor.setAddress("address 1");
        doctor.setPhoneNumber(1234567890);
        doctor.setDOB(Date.valueOf("1987-03-13"));

        // Act
        doctor = doctorDao.addDoctor(doctor);

        // Arrange
        AdultPatient patient = new AdultPatient();
        patient.setEmail("patient@clinic1.com");
        patient.setFirstName("Malcolm");
        patient.setLastName("Ex");
        patient.setPassword("Mal.Com.Ex".hashCode());
        patient.setAddress("address 14");
        patient.setPhoneNumber(1234563890);
        patient.setDOB(Date.valueOf("1989-03-23"));
        patient.setSSN(12345678);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = adultPatientDao.addAdultPatient(patient);

        //Arrange
        Prescription prescription = new Prescription();
        prescription.setDoctorId(doctor.getId());
        prescription.setPatientId(patient.getId());
        prescription.setDate_prescribed(Date.valueOf(LocalDate.now()));
        prescription.setMedicine("Sugar");
        prescription.setDosage("50mg");
        prescription.setQuantity(30);
        prescription.setRefillable(false);

        // Act
        prescription = dao.addPrescription(prescription);
        Prescription prescription1 = dao.getPrescription(prescription.getId());

        // Assert
        assertEquals(prescription, prescription1);

        Prescription prescription2 = new Prescription();
        prescription2.setDoctorId(doctor.getId());
        prescription2.setPatientId(patient.getId());
        prescription2.setDate_prescribed(Date.valueOf(LocalDate.now()));
        prescription2.setMedicine("O2 (Oxygen)");
        prescription2.setDosage("5atm");
        prescription2.setQuantity(30);
        prescription2.setRefillable(false);

        // Act
        prescription2 = dao.addPrescription(prescription2);

        // Assert
        assertNotEquals(prescription1, prescription2);

        // Act
        List<Prescription> prescriptionList = dao.getAllPrescription();
        List<Prescription> prescriptionList1 = dao.getAllPrescriptionsPerDoctor(doctor.getId());
        List<Prescription> prescriptionList2 = dao.getAllPrescriptionsPerPatient(patient.getId());

        // Assert
        assertEquals(prescriptionList.size(), 2);
        assertEquals(prescriptionList, prescriptionList1);
        assertEquals(prescriptionList2, prescriptionList1);

        // Arrange
        List<List<Prescription>> prescriptionLists = Arrays.asList(prescriptionList,
                prescriptionList1,
                prescriptionList2);

        for (List<Prescription> prescriptions: prescriptionLists){
            assertFalse(prescriptions.contains(null));
        }
    }

    @Test
    public void updatePrescription() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Grey");
        doctor.setEmail("jgrey@clinic1.com");
        doctor.setPassword("Doc.John.Grey".hashCode());
        doctor.setAddress("address 1");
        doctor.setPhoneNumber(1234567890);
        doctor.setDOB(Date.valueOf("1987-03-13"));

        // Act
        doctor = doctorDao.addDoctor(doctor);

        // Arrange
        AdultPatient patient = new AdultPatient();
        patient.setEmail("patient@clinic1.com");
        patient.setFirstName("Malcolm");
        patient.setLastName("Ex");
        patient.setPassword("Mal.Com.Ex".hashCode());
        patient.setAddress("address 14");
        patient.setPhoneNumber(1234563890);
        patient.setDOB(Date.valueOf("1989-03-23"));
        patient.setSSN(12345678);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = adultPatientDao.addAdultPatient(patient);

        //Arrange
        Prescription prescription = new Prescription();
        prescription.setDoctorId(doctor.getId());
        prescription.setPatientId(patient.getId());
        prescription.setDate_prescribed(Date.valueOf(LocalDate.now()));
        prescription.setMedicine("Sugar");
        prescription.setDosage("50mg");
        prescription.setQuantity(30);
        prescription.setRefillable(false);

        // Act
        prescription = dao.addPrescription(prescription);
        Prescription prescription1 = dao.getPrescription(prescription.getId());

        // Arrange
        prescription.setRefillable(true);

        // Act
        dao.updatePrescription(prescription);
        Prescription prescription2 = dao.getPrescription(prescription.getId());

        // Assert
        assertNotEquals(prescription1, prescription2);
    }

}