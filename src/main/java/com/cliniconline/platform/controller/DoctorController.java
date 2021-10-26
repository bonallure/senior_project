package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/25/21
 */
@RestController
public class DoctorController implements UserController{


    private final Role ROLE = Role.DOCTOR;

    private DoctorDao doctorDao;
    private AdultPatientDao adultPatientDao;
    private DependentDao dependentDao;
    private MessageDao messageDao;
    private AppointmentDao appointmentDao;
    private PrescriptionDao prescriptionDao;

    @RequestMapping(value = "/doctor/login/{email},{password}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Override
    public User login(@PathVariable String email, @PathVariable String password) {

        Doctor doctor = doctorDao.getDoctorByEmail(email);

        if (doctor.getPassword().equals(password))
            return doctor;
        else
            return null;
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
    public Appointment addAppointment(Appointment appointment) {
        return appointmentDao.addAppointment(appointment);
    }

    @RequestMapping(value = "/doctor/appointment/{appointmentId}", method = RequestMethod.PUT)
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
