package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dao.DoctorDao;
import com.cliniconline.platform.model.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bonallure on 10/22/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdultPatientDaoImplTest {

    @Autowired
    protected AdultPatientDao dao;

    @Autowired
    protected DoctorDao doctorDao;

    @Before
    public void setUp() throws Exception {
        List<AdultPatient> patientList = dao.getAllAdultPatient();

        for(AdultPatient patient : patientList){
            dao.deleteAdultPatient(patient.getEmail());
        }

        List<Doctor> doctorList = doctorDao.getAllDoctors();

        for(Doctor doctor : doctorList){
            doctorDao.deleteDoctor(doctor.getId());
        }
    }
    /*
    * email varchar(50) not null,
        f_name varchar(20) not null,
        l_name varchar(20) not null,
        password varchar(20) not null,
        address varchar(100) not null,
        phone int(10) not null,
        dob date not null,
        ssn int(8) not null,
        role varchar(20) not null,
        doctor_id int
    */
    @Test
    public void addGetDeletePatient() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Grey");
        doctor.setEmail("jgrey@clinic1.com");
        doctor.setPassword("Doc.John.Grey");
        doctor.setAddress("address 1");
        doctor.setPhoneNumber(1234567890);
        doctor.setDOB(Date.valueOf("1987-03-13"));
        doctor.setRole(Role.DOCTOR);

        // Act
        doctor = doctorDao.addDoctor(doctor);

        // Arrange
        AdultPatient patient = new AdultPatient();
        patient.setEmail("patient@clinic1.com");
        patient.setFirstName("Malcolm");
        patient.setLastName("Ex");
        patient.setPassword("Mal.Com.Ex");
        patient.setAddress("address 14");
        patient.setPhoneNumber(1234563890);
        patient.setDOB(Date.valueOf("1989-03-23"));
        patient.setSSN(12345678);
        patient.setRole(Role.PATIENT);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = dao.addAdultPatient(patient);

        AdultPatient patient1 = dao.getPatient(patient.getEmail());

        // Assert the added patient is equal to the patient received
        assertEquals(patient, patient1);

        // Act (delete)
        dao.deleteAdultPatient(patient.getEmail());
        patient1 = dao.getPatient(patient.getEmail());

        // Assert the delete is null
        assertNull(patient1);
    }

    @Test
    public void updatePatient(){
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Grey");
        doctor.setEmail("jgrey@clinic1.com");
        doctor.setPassword("Doc.John.Grey");
        doctor.setAddress("address 1");
        doctor.setPhoneNumber(1234567890);
        doctor.setDOB(Date.valueOf("1987-03-13"));
        doctor.setRole(Role.DOCTOR);

        // Act
        doctor = doctorDao.addDoctor(doctor);

        // Arrange
        AdultPatient patient = new AdultPatient();
        patient.setEmail("patient@clinic1.com");
        patient.setFirstName("Malcolm");
        patient.setLastName("Ex");
        patient.setPassword("Mal.Com.Ex");
        patient.setAddress("address 14");
        patient.setPhoneNumber(1234563890);
        patient.setDOB(Date.valueOf("1989-03-23"));
        patient.setSSN(12345678);
        patient.setRole(Role.PATIENT);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = dao.addAdultPatient(patient);

        AdultPatient patient1 = dao.getPatient(patient.getEmail());
        assertEquals(patient, patient1);

        // Arrange
        patient.changePassword("New/Password");

        // Act
        dao.updateAdultPatient(patient);

        patient = dao.getPatient(patient.getEmail());

        // Assert the two patients are not equal
        assertNotEquals(patient, patient1);
        assertNotNull(patient);
    }

    @Test
    public void getPatientsByDoctorAndDependent(){
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Grey");
        doctor.setEmail("jgrey@clinic1.com");
        doctor.setPassword("Doc.John.Grey");
        doctor.setAddress("address 1");
        doctor.setPhoneNumber(1234567890);
        doctor.setDOB(Date.valueOf("1987-03-13"));
        doctor.setRole(Role.DOCTOR);

        // Act
        doctor = doctorDao.addDoctor(doctor);

        // Arrange
        AdultPatient patient = new AdultPatient();
        patient.setEmail("patient@clinic1.com");
        patient.setFirstName("Malcolm");
        patient.setLastName("Ex");
        patient.setPassword("Mal.Com.Ex");
        patient.setAddress("address 14");
        patient.setPhoneNumber(1234563890);
        patient.setDOB(Date.valueOf("1989-03-23"));
        patient.setSSN(12345678);
        patient.setRole(Role.PATIENT);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = dao.addAdultPatient(patient);

        List<AdultPatient> patients = dao.getAllAdultPatientByDoctor(doctor.getId());

        // Assert the patient was successfully retrieved
        assertEquals(1, patients.size());
        assertTrue(patients.contains(patient));

        // Arrange
        Dependent dependent = new Dependent();
        dependent.setGuardianId(patient.getId());

        // Act
        AdultPatient guardian = dao.getGuardian(dependent);

        // Assert the guardian is the patient
        assertEquals(guardian, patient);
    }
}