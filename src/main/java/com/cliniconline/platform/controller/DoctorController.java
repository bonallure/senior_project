package com.cliniconline.platform.controller;

import com.cliniconline.platform.PlatformApplication;
import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.model.viewmodel.DoctorViewModel;
import com.cliniconline.platform.service.DoctorServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.sql.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by bonallure on 10/25/21
 */
@RestController
@CrossOrigin
public class DoctorController implements UserControllers {

    public class Shift{
        private boolean onShift;
        private Date time;
    }

    private final Role ROLE = Role.DOCTOR;

    @Autowired
    protected DoctorDao doctorDao;

    @Autowired
    protected AdultPatientDao adultPatientDao;

    @Autowired
    protected DependentDao dependentDao;

    @Autowired
    protected MessageDao messageDao;

    @Autowired
    protected AppointmentDao appointmentDao;

    @Autowired
    protected PrescriptionDao prescriptionDao;

    @Autowired
    protected DoctorServiceLayer serviceLayer;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/doctor/login/{email}", method = RequestMethod.GET)
    @Override
    public ResponseEntity<Object> login(@PathVariable String email) {
        Doctor doctor = doctorDao.getDoctorByEmail(email);
        PlatformApplication.LOGGER.info(email+ " has logged in");
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/account/{email}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public User viewAccount(@PathVariable String email) {
        return doctorDao.getDoctorByEmail(email);
    }

    @RequestMapping(value = "/doctor/patients/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public Set<User> getPatients(@PathVariable int doctorId) {
        Set<User> patients =  new HashSet<>(dependentDao.getAllDependentsPerDoctor(doctorId));
        patients.addAll(adultPatientDao.getAllAdultPatientByDoctor(doctorId));

        return patients;
    }

    @RequestMapping(value = "/doctor/message", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public Message sendMessage(@RequestBody Message message) {
        return messageDao.addMessage(message);
    }

    @RequestMapping(value = "/doctor/message/{messageId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Message viewMessage(@PathVariable int messageId) {
        return messageDao.getMessage(messageId);
    }

    @RequestMapping(value = "/doctor/messages/inbox/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Set<Message> viewInbox(@PathVariable int doctorId) {
        return new HashSet<>(messageDao.getDoctorInbox(doctorId));
    }

    @RequestMapping(value = "/doctor/messages/outbox/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Set<Message> viewOutbox(@PathVariable int doctorId) {
        return new HashSet<>(messageDao.getDoctorOutbox(doctorId));
    }

    @RequestMapping(value = "/doctor/appointments/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Set<Appointment> viewAppointments(@PathVariable int doctorId) {
        return new HashSet<>(appointmentDao.getAllAppointmentsPerDoctor(doctorId));
    }

    @RequestMapping(value = "/doctor/appointment/{appointmentId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Appointment viewAppointment(@PathVariable int appointmentId) {
        return appointmentDao.getAppointment(appointmentId);
    }

    @Override
    public void joinAppointment(int appointment, int user_id) {

    }

    @Override
    public void endAppointment(int appointmentId) {

    }

    @RequestMapping(value = "/doctor/appointment", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public Appointment addAppointment(Map appointment) {
        return appointmentDao.addAppointment((Appointment) appointment);
    }

    @RequestMapping(value = "/doctor/appointment/{appointmentId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public void cancelAppointment(@PathVariable int appointmentId) {
        appointmentDao.deleteAppointment(appointmentId);
    }

    @RequestMapping(value = "/doctor/prescriptions/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Set<Prescription> viewPrescriptions(@PathVariable int doctorId) {
        return new HashSet<>(prescriptionDao.getAllPrescriptionsPerDoctor(doctorId));
    }

    @RequestMapping(value = "/patient/prescriptions/patient/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public Set<Prescription> viewPatientPrescriptions(@PathVariable int patientId) {
        return new HashSet<>(prescriptionDao.getAllPrescriptionsPerPatient(patientId));
    }


    @RequestMapping(value = "/doctor/prescription/{prescriptionId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Prescription viewPrescription(@PathVariable int prescriptionId) {
        return prescriptionDao.getPrescription(prescriptionId);
    }

    @RequestMapping(value = "/doctor/prescription", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Prescription writePrescription(Prescription prescription){
        return prescriptionDao.addPrescription(prescription);
    }
}
