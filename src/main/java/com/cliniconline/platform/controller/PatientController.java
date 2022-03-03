package com.cliniconline.platform.controller;

import com.cliniconline.platform.PlatformApplication;
import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.model.viewmodel.PatientViewModel;
import com.cliniconline.platform.service.PatientServiceLayer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Map;
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

    private Logger logger = PlatformApplication.LOGGER;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Logging in a patient
    @RequestMapping(value = "/patient/login/{email}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Override
    public ResponseEntity<Object> login(@PathVariable String email) {
        AdultPatient adultPatient = adultPatientDao.getPatient("email");
        PatientViewModel viewModel = patientServiceLayer.findPatient(adultPatient.getId());

        return new ResponseEntity<>(viewModel, HttpStatus.OK);
    }


    @RequestMapping(value = "/patient/account/{email}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public ResponseEntity<Object> viewAccount(@PathVariable String email) {
        Patient patient =  adultPatientDao.getPatient(email);
        PlatformApplication.LOGGER.info(email, " requested their account information");

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/dependents/{adultPatientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Set<Dependent> getDependents(@PathVariable int adultPatientId) {
        return new HashSet<>(dependentDao.getAllDependentsPerAdultPatient(adultPatientId));
    }

    @RequestMapping(value = "/patient/message", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public ResponseEntity<Object> sendMessage(@RequestBody Message message) {
        return new ResponseEntity<>(messageDao.addMessage(message), HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/message/{messageId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public ResponseEntity<Object> viewMessage(@PathVariable int messageId) {

        return new ResponseEntity<>(messageDao.getMessage(messageId), HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/messages/inbox/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public ResponseEntity<Object> viewInbox(@PathVariable int patientId) {
        Set<Message> inbox = new HashSet<>(messageDao.getPatientInbox(patientId));

        return new ResponseEntity<>(inbox, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/messages/outbox/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public ResponseEntity<Object> viewOutbox(@PathVariable int patientId) {
        Set<Message> outbox =  new HashSet<>(messageDao.getPatientOutbox(patientId));

        return new ResponseEntity<>(outbox, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/appointments/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public ResponseEntity<Object> viewAppointments(@PathVariable int patientId) {
        Set<Appointment> appointments = new HashSet<>(appointmentDao.getAllAppointmentsPerPatient(patientId));

        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/appointment/{appointmentId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public ResponseEntity<Object> viewAppointment(@PathVariable int appointmentId) {
        Appointment appointment = appointmentDao.getAppointment(appointmentId);

        return new ResponseEntity<>(appointment, HttpStatus.OK);
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
    public ResponseEntity<Object> addAppointment(@RequestBody Appointment appointment) {
        appointment.setRequester(ROLE);
        Appointment newAppointment = appointmentDao.addAppointment(appointment);
        logger.info("New appointment added. appointmentId: "+ newAppointment.getId());

        return new ResponseEntity<>(newAppointment, HttpStatus.OK);
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
    public ResponseEntity<Object> viewPrescriptions(@PathVariable int patientId) {
        Set<Prescription> prescriptions = new HashSet<>(prescriptionDao.getAllPrescriptionsPerPatient(patientId));

        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/prescription/{prescriptionId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public ResponseEntity<Object> viewPrescription(@PathVariable int prescriptionId) {
        Prescription prescription = prescriptionDao.getPrescription(prescriptionId);

        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }
}
