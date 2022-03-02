package com.cliniconline.platform.controller;

import com.cliniconline.platform.PlatformApplication;
import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.model.viewmodel.DoctorViewModel;
import com.cliniconline.platform.service.DoctorServiceLayer;
import com.sun.tools.jconsole.JConsoleContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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


    /**************************************************************************************** ACCOUNT ****************/
    @RequestMapping(value = "/doctor/login/{email}", method = RequestMethod.GET)
    @Override
    public ResponseEntity<Object> login(@PathVariable String email) {
        Doctor doctor = doctorDao.getDoctorByEmail(email);
        DoctorViewModel viewModel = serviceLayer.findDoctor(doctor.getId());
        logger.info(email+ " has logged in");
        logger.info(viewModel.toString());

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

    /**************************************************************************************** MESSAGE ****************/
    @RequestMapping(value = "/doctor/message", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public ResponseEntity<Object> sendMessage(@RequestBody Message message) {
        logger.info(message.getDate()+"T"+message.getTime()+ ": New message from doctor with id: "+ message.getDoctorId());
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

    /************************************************************************************ APPOINTMENT ****************/
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

    @RequestMapping(value = "/doctor/addAppointment", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Override
    public ResponseEntity<Object> addAppointment(@RequestBody Appointment appointment) {
        logger.info("New doctor appointment");
        Appointment newAppointment = appointmentDao.addAppointment(appointment);
        logger.info("New appointment added. appointmentId: "+ newAppointment.getId());

        return new ResponseEntity<>(newAppointment, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/appointment/{appointmentId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public void cancelAppointment(@PathVariable int appointmentId) {
        appointmentDao.deleteAppointment(appointmentId);
    }

    /*********************************************************************************** PRESCRIPTION ****************/
    @RequestMapping(value = "/doctor/prescriptions/{doctorId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public ResponseEntity<Object> viewPrescriptions(@PathVariable int doctorId) {
        Set<Prescription> prescriptions = new HashSet<>(prescriptionDao.getAllPrescriptionsPerDoctor(doctorId));

        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/prescriptions/patient/{patientId}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/doctor/prescription", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Object> writePrescription(@RequestBody Prescription prescription){
        Prescription prescription1 = prescriptionDao.addPrescription(prescription);

        return new ResponseEntity<>(prescription1, HttpStatus.OK);
    }

    /*****************************************************************************************************************/
    @Override
    public void joinAppointment(int appointment, int user_id) {

    }

    @Override
    public void endAppointment(int appointmentId) {
    }
}
