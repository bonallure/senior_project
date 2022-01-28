package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dao.DependentDao;
import com.cliniconline.platform.model.dao.DoctorDao;
import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Dependent;
import com.cliniconline.platform.model.dto.Doctor;
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
 * Created by bonallure on 10/23/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DependentDaoImplTest {

    @Autowired
    protected AdultPatientDao adultPatientDao;

    @Autowired
    protected DoctorDao doctorDao;

    @Autowired
    protected DependentDao dao;

    @Before
    public void setUp() throws Exception {
        List<Dependent> dependentList = dao.getAllDependents();

        for(Dependent dependent : dependentList){
            dao.deleteDependent(dependent.getId());
        }
        List<AdultPatient> patientList = adultPatientDao.getAllAdultPatient();

        for(AdultPatient patient : patientList){
            adultPatientDao.deleteAdultPatient(patient.getEmail());
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
        guardian_id int not null,
        doctor_id int
    */
        @Test
        public void addGetDeleteDependent() {
            // Arrange
            Doctor doctor = new Doctor();
            doctor.setFirstName("John");
            doctor.setLastName("Grey");
            doctor.setEmail("jgrey@clinic1.com");
            doctor.setPassword("Doc.John.Grey".hashCode());
            doctor.setAddress("address 1");
            doctor.setPhoneNumber(1234567890L);
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
            patient.setPhoneNumber(1234563890L);
            patient.setDOB(Date.valueOf("1989-03-23"));
            patient.setSSN(12345678);
            patient.setDoctorId(doctor.getId());

            // Act
            patient = adultPatientDao.addAdultPatient(patient);

            // Arrange
            Dependent dependent = new Dependent();
            dependent.setEmail(patient.getEmail());
            dependent.setFirstName("Baby Boy");
            dependent.setLastName("Molina");
            dependent.setPassword(patient.getPassword());
            dependent.setAddress("address 4");
            dependent.setPhoneNumber(patient.getPhoneNumber());
            dependent.setDOB(Date.valueOf("2008-09-14"));
            dependent.setSSN(44786539);
            dependent.setGuardianId(patient.getId());
            dependent.setDoctorId(doctor.getId());

            // Act
            dependent = dao.addDependent(dependent);
            Dependent dependent1 = dao.getDependent(dependent.getId());

            // Assert the added patient is equal to the patient received
            assertEquals(dependent1, dependent);

            // Act
            List<Dependent> dependentsPerDoctor = dao.getAllDependentsPerDoctor(doctor.getId());
            List<Dependent> dependentsPerAdultPatient = dao.getAllDependentsPerAdultPatient(patient.getId());

            // Assert that the get by doctor/adult patient are equal and valid
            assertEquals(1, dependentsPerDoctor.size());
            assertEquals(1, dependentsPerAdultPatient.size());
            assertEquals(dependentsPerDoctor, dependentsPerAdultPatient);
            assertTrue(dependentsPerDoctor.contains(dependent));

            // Act (delete)
            dao.deleteDependent(dependent.getId());
            dependent1 = dao.getDependent(dependent.getId());

            // Assert the delete is null
            assertNull(dependent1);
        }

    @Test
    public void updateDependent(){
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Grey");
        doctor.setEmail("jgrey@clinic1.com");
        doctor.setPassword("Doc.John.Grey".hashCode());
        doctor.setAddress("address 1");
        doctor.setPhoneNumber(1234567890L);
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
        patient.setPhoneNumber(1234563890L);
        patient.setDOB(Date.valueOf("1989-03-23"));
        patient.setSSN(12345678);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = adultPatientDao.addAdultPatient(patient);

        // Arrange
        Dependent dependent = new Dependent();
        dependent.setEmail(patient.getEmail());
        dependent.setFirstName("Baby Boy");
        dependent.setLastName("Molina");
        dependent.setPassword(patient.getPassword());
        dependent.setAddress("address 4");
        dependent.setPhoneNumber(patient.getPhoneNumber());
        dependent.setDOB(Date.valueOf("2008-09-14"));
        dependent.setSSN(44786539);
        dependent.setGuardianId(patient.getId());
        dependent.setDoctorId(doctor.getId());

        // Act
        dependent = dao.addDependent(dependent);

        Dependent dependent1 = dao.getDependent(dependent.getId());
        assertEquals(dependent1, dependent);

        // Arrange
        dependent.setEmail("babyboy@gmail.com");

        // Act
        dao.updateDependent(dependent);

        dependent = dao.getDependent(dependent.getId());

        // Assert the two patients are not equal
        assertNotEquals(dependent, dependent1);
        assertNotNull(dependent);
    }
}