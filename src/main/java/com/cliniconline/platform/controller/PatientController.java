package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by bonallure on 10/25/21
 */
@RestController
@CrossOrigin
public class PatientController implements UserController {

    // Patient Role
    private final Role ROLE = Role.PATIENT;

    // Wiring the data access objects
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

    // Wiring the controller
    @Autowired
    public PatientController(){
    }

    // Get the login page # not using
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login Page";
    }

    // Logging in a patient
    @RequestMapping(value = "/patient/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Override
    public User login(@RequestBody Map adultPatient) {
        AdultPatient adultPatient1 = adultPatientDao.getPatient((String) adultPatient.get("email"));

        if (adultPatient1.getPassword().equals( (String) adultPatient.get("password")))
            return adultPatient1;
        else
            return null;
    }


    @RequestMapping(value = "/patient/account/{email}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public User viewAccount(@PathVariable String email) {

        // System.out.println(email);
        return adultPatientDao.getPatient(email);
    }

    @RequestMapping(value = "/patient/dependents/{adultPatientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
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
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Message viewMessage(@PathVariable int messageId) {
        return messageDao.getMessage(messageId);
    }

    @RequestMapping(value = "/patient/messages/inbox/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Set<Message> viewInbox(@PathVariable int patientId) {
        return new HashSet<>(messageDao.getPatientInbox(patientId));
    }

    @RequestMapping(value = "/patient/messages/outbox/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Set<Message> viewOutbox(@PathVariable int patientId) {
        return new HashSet<>(messageDao.getPatientOutbox(patientId));
    }

    @RequestMapping(value = "/patient/appointments/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Set<Appointment> viewAppointments(@PathVariable int patientId) {
        return new HashSet<>(appointmentDao.getAllAppointmentsPerPatient(patientId));
    }

    @RequestMapping(value = "/patient/appointment/{appointmentId}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/patient/appointment", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public Appointment addAppointment(Appointment appointment) {
        return appointmentDao.addAppointment(appointment);
    }

    @RequestMapping(value = "/patient/appointment/{appointmentId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public void cancelAppointment(@PathVariable int appointmentId) {
        appointmentDao.deleteAppointment(appointmentId);
    }

    @RequestMapping(value = "/patient/prescriptions/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Set<Prescription> viewPrescriptions(@PathVariable int patientId) {
        return new HashSet<>(prescriptionDao.getAllPrescriptionsPerPatient(patientId));
    }

    @RequestMapping(value = "/patient/prescription/{prescriptionId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Prescription viewPrescription(@PathVariable int prescriptionId) {
        return prescriptionDao.getPrescription(prescriptionId);
    }
}
