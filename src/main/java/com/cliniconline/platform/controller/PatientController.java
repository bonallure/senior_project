package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.service.PatientServiceLayer;
import com.cliniconline.platform.model.viewmodel.PatientViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by bonallure on 10/25/21
 */
@RestController
@CrossOrigin
public class PatientController implements UserControllers {

    // Patient Role
    private final Role ROLE = Role.PATIENT;

    // Autowiring the data access objects
    @Autowired
    private AdultPatientDao adultPatientDao;

    @Autowired
    private DependentDao dependentDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private PrescriptionDao prescriptionDao;

    // Wiring the service layer
    @Autowired
    private PatientServiceLayer patientServiceLayer;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Logging in a patient
    @RequestMapping(value = "/patient/login/{email}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Override
    public ResponseEntity<User> login(@PathVariable String email) {
        AdultPatient adultPatient = adultPatientDao.getPatient("email");

            return ResponseEntity.ok().body(adultPatient);
    }


    @RequestMapping(value = "/patient/account/{email}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public User viewAccount(@PathVariable String email) {

        // System.out.println(email);
        return adultPatientDao.getPatient(email);
    }

    @RequestMapping(value = "/patient/dependents/{adultPatientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Set<Dependent> getDependents(@PathVariable int adultPatientId) {
        return new HashSet<>(dependentDao.getAllDependentsPerAdultPatient(adultPatientId));
    }

    @RequestMapping(value = "/patient/message", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public Message sendMessage(@RequestBody Message message) {
        return messageDao.addMessage(message);
    }

    @RequestMapping(value = "/patient/message/{messageId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public Message viewMessage(@PathVariable int messageId) {
        return messageDao.getMessage(messageId);
    }

    @RequestMapping(value = "/patient/messages/inbox/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public Set<Message> viewInbox(@PathVariable int patientId) {
        return new HashSet<>(messageDao.getPatientInbox(patientId));
    }

    @RequestMapping(value = "/patient/messages/outbox/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public Set<Message> viewOutbox(@PathVariable int patientId) {
        return new HashSet<>(messageDao.getPatientOutbox(patientId));
    }

    @RequestMapping(value = "/patient/appointments/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public Set<Appointment> viewAppointments(@PathVariable int patientId) {
        return new HashSet<>(appointmentDao.getAllAppointmentsPerPatient(patientId));
    }

    @RequestMapping(value = "/patient/appointment/{appointmentId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
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

    @RequestMapping(value = "/patient/appointment", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public Appointment addAppointment(@RequestBody Map appointment) {

        System.out.println(appointment);
        Appointment newAppointment = new Appointment();
        newAppointment.setPatientId((Integer) appointment.get("patientId"));
        newAppointment.setDoctorId((int) appointment.get("doctorId"));
        newAppointment.setLink((String) appointment.get("link"));
        newAppointment.setType("VIDEO");
        newAppointment.setNote((String) appointment.get("note"));
        newAppointment.setDate(Date.valueOf((String) appointment.get("date")));
        return appointmentDao.addAppointment(newAppointment);
    }

    @RequestMapping(value = "/patient/appointment/{appointmentId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public void cancelAppointment(@PathVariable int appointmentId) {
        appointmentDao.deleteAppointment(appointmentId);
    }

    @RequestMapping(value = "/patient/prescriptions/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public Set<Prescription> viewPrescriptions(@PathVariable int patientId) {
        return new HashSet<>(prescriptionDao.getAllPrescriptionsPerPatient(patientId));
    }

    @RequestMapping(value = "/patient/prescription/{prescriptionId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public Prescription viewPrescription(@PathVariable int prescriptionId) {
        return prescriptionDao.getPrescription(prescriptionId);
    }
}
