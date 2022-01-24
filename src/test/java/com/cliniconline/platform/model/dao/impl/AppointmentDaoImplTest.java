package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dao.AppointmentDao;
import com.cliniconline.platform.model.dao.DoctorDao;
import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Appointment;
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
public class AppointmentDaoImplTest {

    @Autowired
    protected AdultPatientDao adultPatientDao;

    @Autowired
    protected DoctorDao doctorDao;

    @Autowired
    protected AppointmentDao dao;

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

        List<Appointment> appointmentList = dao.getAllAppointments();
        for (Appointment appointment : appointmentList) {
            dao.deleteAppointment(appointment.getId());
        }
    }

    /*
doctor_id int not null,
patient_id int not null,
date date not null,
link varchar(100),
location varchar(100),
type varchar(20),
confirmed boolean not null,
note mediumtext
*/
    @Test
    public void addGetDeleteAppointment() {
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
        patient.setSSN(12345678L);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = adultPatientDao.addAdultPatient(patient);

        // Arrange
        Appointment appointment = new Appointment();
        appointment.setDoctorId(doctor.getId());
        appointment.setPatientId(patient.getId());
        appointment.setDate(Date.valueOf("2021-10-29"));
        appointment.setLink("google.com");
        appointment.setLocation("");
        appointment.setType("video");
        appointment.setConfirmed(false);
        appointment.setNote("");

        // Act
        appointment = dao.addAppointment(appointment);

        Appointment appointment1 = dao.getAppointment(appointment.getId());

        // Assert the added patient is equal to the patient received
        assertEquals(appointment, appointment1);

        // Act
        List<Appointment> appointmentList = dao.getAllAppointments();
        List<Appointment> appointmentList1 = dao.getAllAppointmentsPerDoctor(doctor.getId());
        List<Appointment> appointmentList2 = dao.getAllAppointmentsPerPatient(patient.getId());

        // Assert that the get by doctor/adult/all appointments are equal and valid
        assertEquals(1, appointmentList.size());
        assertEquals(1, appointmentList1.size());
        assertEquals(1, appointmentList2.size());
        assertEquals(appointmentList1, appointmentList1);
        assertEquals(appointmentList, appointmentList1);
        assertTrue(appointmentList.contains(appointment));

        // Act (delete)
        dao.deleteAppointment(appointment.getId());
        appointment1 = dao.getAppointment(appointment.getId());

        // Assert the delete is null
        assertNull(appointment1);
    }

    @Test
    public void updateAppointment(){
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
        patient.setSSN(12345678L);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = adultPatientDao.addAdultPatient(patient);

        // Arrange
        Appointment appointment = new Appointment();
        appointment.setDoctorId(doctor.getId());
        appointment.setPatientId(patient.getId());
        appointment.setDate(Date.valueOf("2021-10-29"));
        appointment.setLink("google.com");
        appointment.setLocation("");
        appointment.setType("video");
        appointment.setConfirmed(false);
        appointment.setNote("");

        // Act
        appointment = dao.addAppointment(appointment);

        Appointment appointment1 = dao.getAppointment(appointment.getId());

        // Arrange
        appointment.setDate(Date.valueOf("2021-11-03"));

        // Act (update)
        dao.updateAppointment(appointment);
        appointment = dao.getAppointment(appointment.getId());

        // Assert the two appointments are not equal
        assertNotEquals(appointment, appointment1);
        assertNotNull(appointment);
    }

}