package com.cliniconline.platform.controller;

import com.cliniconline.platform.PlatformApplication;
import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.model.viewmodel.DoctorViewModel;
import com.cliniconline.platform.service.DoctorServiceLayer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private Logger logger = PlatformApplication.LOGGER;

    @RequestMapping(value = "/doctor/login/{email}", method = RequestMethod.GET)
    @Override
    public ResponseEntity<Object> login(@PathVariable String email) {
        Doctor doctor = doctorDao.getDoctorByEmail(email);
        DoctorViewModel viewModel = serviceLayer.findDoctor(doctor.getId());
        PlatformApplication.LOGGER.info(email+ " has logged in");

        return new ResponseEntity<>(viewModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/account/{email}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public ResponseEntity<Object> viewAccount(@PathVariable String email) {
        Doctor doctor = doctorDao.getDoctorByEmail(email);
        PlatformApplication.LOGGER.info(email+ " requested their account information");
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/patients/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public ResponseEntity<Object> getPatients(@PathVariable int doctorId) {
        Set<User> patients =  new HashSet<>(dependentDao.getAllDependentsPerDoctor(doctorId));
        patients.addAll(adultPatientDao.getAllAdultPatientByDoctor(doctorId));


        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/message", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public ResponseEntity<Object> sendMessage(@RequestBody Message message) {

        return new ResponseEntity<>(messageDao.addMessage(message), HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/message/{messageId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public ResponseEntity<Object> viewMessage(@PathVariable int messageId) {
        return new ResponseEntity<>(messageDao.getMessage(messageId), HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/messages/inbox/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public ResponseEntity<Object> viewInbox(@PathVariable int doctorId) {
        Set<Message> inbox = new HashSet<>(messageDao.getDoctorInbox(doctorId));

        return new ResponseEntity<>(inbox, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/messages/outbox/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public ResponseEntity<Object> viewOutbox(@PathVariable int doctorId) {
        Set<Message> outbox = new HashSet<>(messageDao.getDoctorOutbox(doctorId));

        return new ResponseEntity<>(outbox, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/appointments/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public ResponseEntity<Object> viewAppointments(@PathVariable int doctorId) {
        Set<Appointment> appointments = new HashSet<>(appointmentDao.getAllAppointmentsPerDoctor(doctorId));

        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/appointment/{appointmentId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
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

    @RequestMapping(value = "/doctor/addAppointment", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public ResponseEntity<Object> addAppointment(@RequestBody Map appointment) {
        logger.info("New doctor appointment");
        logger.info(appointment.toString());
        Appointment newAppointment = new Appointment();
        newAppointment.setPatientId((Integer) appointment.get("patientId"));
        newAppointment.setDoctorId((int) appointment.get("doctorId"));
        newAppointment.setLink((String) appointment.get("link"));
        newAppointment.setType("VIDEO");
        newAppointment.setNote((String) appointment.get("note"));
        newAppointment.setDate(Date.valueOf((String) appointment.get("date")));

        newAppointment = appointmentDao.addAppointment(newAppointment);
        logger.info("New appointment added. appointmentId: "+newAppointment.getId());
        return new ResponseEntity<>(newAppointment, HttpStatus.OK);
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
    public ResponseEntity<Object> viewPrescriptions(@PathVariable int doctorId) {
        Set<Prescription> prescriptions = new HashSet<>(prescriptionDao.getAllPrescriptionsPerDoctor(doctorId));

        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/prescriptions/patient/{patientId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public ResponseEntity<Object> viewPatientPrescriptions(@PathVariable int patientId) {
        Set<Prescription> prescriptions = new HashSet<>(prescriptionDao.getAllPrescriptionsPerPatient(patientId));

        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }


    @RequestMapping(value = "/doctor/prescription/{prescriptionId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public ResponseEntity<Object> viewPrescription(@PathVariable int prescriptionId) {
        Prescription prescription = prescriptionDao.getPrescription(prescriptionId);

        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/prescription", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Object> writePrescription(Prescription prescription){
        Prescription prescription1 = prescriptionDao.addPrescription(prescription);

        return new ResponseEntity<>(prescription1, HttpStatus.OK);
    }
}
