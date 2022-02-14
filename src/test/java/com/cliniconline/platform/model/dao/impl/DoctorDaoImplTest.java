package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.DoctorDao;
import com.cliniconline.platform.model.dto.Doctor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bonallure on 10/22/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DoctorDaoImplTest {

    @Autowired
    protected DoctorDao dao;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Before
    public void setUp() throws Exception {
        List<Doctor> doctorList = dao.getAllDoctors();

        for(Doctor doctor : doctorList){
            dao.deleteDoctor(doctor.getId());
        }
    }
/*  private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private int phoneNumber;
    private Date DOB;
    private final Role role = Role.DOCTOR;
*/
    @Test
    public void addGetDeleteDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Grey");
        doctor.setEmail("jgrey@clinic1.com");
        doctor.setPassword(passwordEncoder.encode("Doc.John.Grey"));
        doctor.setAddress("address 1");
        doctor.setPhoneNumber(1234567890L);
        doctor.setDOB(Date.valueOf("1987-03-13"));

        // Act
        doctor = dao.addDoctor(doctor);

        Doctor doctor1 = dao.getDoctor(doctor.getId());

        // Assert the added doctor is equal to the doctor received
        assertEquals(doctor, doctor1);

        // Act (delete)
        dao.deleteDoctor(doctor.getId());
        doctor1 = dao.getDoctor(doctor.getId());

        // Assert the delete is null
        assertNull(doctor1);
    }


    @Test
    public void updateDoctor() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Grey");
        doctor.setEmail("jgrey@clinic1.com");
        doctor.setPassword(passwordEncoder.encode("Doc.John.Grey"));
        doctor.setAddress("address 1");
        doctor.setPhoneNumber(1234567890L);
        doctor.setDOB(Date.valueOf("1987-03-13"));

        // Act
        doctor = dao.addDoctor(doctor);

        Doctor doctor1 = dao.getDoctor(doctor.getId());

        // Assert the added doctor is equal to the doctor received
        assertEquals(doctor, doctor1);

        // Arrange
        doctor.setEmail("jgrey1@clinic1.com");

        // Act
        dao.updateDoctor(doctor);

        doctor = dao.getDoctor(doctor.getId());

        // Assert the two doctors are not equal to one another
        assertNotEquals(doctor, doctor1);
        assertNotNull(doctor);
    }
}