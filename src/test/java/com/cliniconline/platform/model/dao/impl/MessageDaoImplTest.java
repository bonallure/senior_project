package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dao.DoctorDao;
import com.cliniconline.platform.model.dao.MessageDao;
import com.cliniconline.platform.model.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bonallure on 10/23/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageDaoImplTest {

    @Autowired
    protected AdultPatientDao adultPatientDao;

    @Autowired
    protected DoctorDao doctorDao;

    @Autowired
    protected MessageDao dao;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

        List<Message> messageList = dao.getAllMessages();
        for (Message message : messageList) {
            dao.deleteMessage(message.getId());
        }
    }

    @Test
    public void addGetDeleteMessage() {
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
        doctor = doctorDao.addDoctor(doctor);

        // Arrange
        AdultPatient patient = new AdultPatient();
        patient.setEmail("patient@clinic1.com");
        patient.setFirstName("Malcolm");
        patient.setLastName("Ex");
        patient.setPassword(passwordEncoder.encode("Mal.Com.Ex"));
        patient.setAddress("address 14");
        patient.setPhoneNumber(1234563890L);
        patient.setDOB(Date.valueOf("1989-03-23"));
        patient.setSSN(12345678L);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = adultPatientDao.addAdultPatient(patient);

        // Arrange
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        now.setTime(0);
        Message message = new Message();
        message.setRecipientRole(doctor.getRole());
        message.setSenderRole(patient.getRole());
        message.setDoctorId(doctor.getId());
        message.setPatientId(patient.getId());
        message.setDate(Date.valueOf(now.toString()));
        message.setTime(Time.valueOf(LocalTime.now()));
        message.setMessage("Hi Doctor!");

        // Act
        message = dao.addMessage(message);

        Message message1 = dao.getMessage(message.getId());

        // Assert the added message is equal to the message received
        assertEquals(message, message1);

        // Arrange
        Message message2 = message1.reply("Hi Patient");

        // Act
        message2 = dao.addMessage(message2);

        // Assert the added message is equal to the message received
        assertNotEquals(message1, message2);

        // Act
        List<Message> messageList = dao.getAllMessages();
        List<Message> messageList1 = dao.getDoctorInbox(doctor.getId());
        List<Message> messageList2 = dao.getPatientInbox(patient.getId());
        List<Message> messageList3 = dao.getDoctorOutbox(doctor.getId());
        List<Message> messageList4 = dao.getPatientOutbox(patient.getId());

        // Assert that the get all are valid
        assertEquals(2, messageList.size());
        assertTrue(messageList.contains(message1));
        assertTrue(messageList.contains(message2));
        assertEquals(1, messageList1.size());
        assertEquals(1, messageList2.size());
        assertEquals(1, messageList3.size());
        assertEquals(1, messageList4.size());
        assertTrue(messageList1.contains(message1));
        assertTrue(messageList2.contains(message2));
        assertTrue(messageList3.contains(message2));
        assertTrue(messageList4.contains(message1));
        List<List<Message>> mailBoxes = Arrays.asList(messageList,
                messageList1,
                messageList2,
                messageList3,
                messageList4);

        for(List<Message> mailbox: mailBoxes){
            assertFalse(mailbox.contains(null));
        }
        // Act (delete)
        dao.deleteMessage(message.getId());
        message1 = dao.getMessage(message.getId());

        // Assert the delete is null
        assertNull(message1);
    }

    @Test
    public void updateMessage(){
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
        doctor = doctorDao.addDoctor(doctor);

        // Arrange
        AdultPatient patient = new AdultPatient();
        patient.setEmail("patient@clinic1.com");
        patient.setFirstName("Malcolm");
        patient.setLastName("Ex");
        patient.setPassword(passwordEncoder.encode("Mal.Com.Ex"));
        patient.setAddress("address 14");
        patient.setPhoneNumber(1234563890L);
        patient.setDOB(Date.valueOf("1989-03-23"));
        patient.setSSN(12345678L);
        patient.setDoctorId(doctor.getId());

        // Act
        patient = adultPatientDao.addAdultPatient(patient);

        // Arrange
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        now.setTime(0);
        Message message = new Message();
        message.setRecipientRole(doctor.getRole());
        message.setSenderRole(patient.getRole());
        message.setDoctorId(doctor.getId());
        message.setPatientId(patient.getId());
        message.setDate(now);
        message.setTime(Time.valueOf(LocalTime.now()));
        message.setMessage("Hi Doctor!");

        // Act
        message = dao.addMessage(message);

        Message message1 = dao.getMessage(message.getId());

        // Assert the added message is equal to the message received
        assertEquals(message, message1);

        // Act
        message.setMessage("test message");
        dao.updateMessage(message);

        Message message2 = dao.getMessage(message.getId());

        // Assert
        assertNotEquals(message1, message2);
    }
}