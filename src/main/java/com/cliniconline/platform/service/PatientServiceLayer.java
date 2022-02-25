package com.cliniconline.platform.service;

import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.model.viewmodel.PatientViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bonallure on 11/8/21
 */
@Service
public class PatientServiceLayer implements ServiceLayer {

    // adding the dao
    private DoctorDao doctorDao;
    private AdultPatientDao adultPatientDao;
    private DependentDao dependentDao;
    private MessageDao messageDao;
    private AppointmentDao appointmentDao;
    private PrescriptionDao prescriptionDao;

    @Autowired
    public PatientServiceLayer(DoctorDao doctorDao, AdultPatientDao adultPatientDao, DependentDao dependentDao,
                               MessageDao messageDao, AppointmentDao appointmentDao, PrescriptionDao prescriptionDao){

        this.doctorDao = doctorDao;
        this.adultPatientDao = adultPatientDao;
        this.dependentDao = dependentDao;
        this.messageDao = messageDao;
        this.appointmentDao = appointmentDao;
        this.prescriptionDao = prescriptionDao;
    }

    public PatientViewModel findPatient(int id){

        //Getting the patient
        /*TODO
        *  Allow for adultPatient or dependent selection*/
        AdultPatient adultPatient = adultPatientDao.getPatientById(id);

        return buildPatientViewModel(adultPatient);
    }

    private PatientViewModel buildPatientViewModel(AdultPatient adultPatient) {

        // getting the service items
        List<Appointment> appointments = sortAppointments(appointmentDao.getAllAppointments());
        List<Message> outbox = messageDao.getPatientOutbox(adultPatient.getId());
        List<Message> inbox = messageDao.getPatientInbox(adultPatient.getId());
        List<Prescription> prescriptions = prescriptionDao.getAllPrescription();
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctorDao.getDoctor(adultPatient.getDoctorId()));

        PatientViewModel pvm = new PatientViewModel();
        pvm.setPatient(adultPatient);
        pvm.setId(adultPatient.getId());
        pvm.setEmail(adultPatient.getEmail());
        pvm.setFirstName(adultPatient.getFirstName());
        pvm.setLastName((adultPatient.getLastName()));
        pvm.setRole(adultPatient.getRole());
        pvm.setAppointments(appointments);
        pvm.setOutbox(outbox);
        pvm.setInbox(inbox);
        pvm.setDoctors(doctors);
        pvm.setPrescriptions(prescriptions);

        return pvm;
    }

    private List<Appointment> sortAppointments(List<Appointment> appointments) {
        appointments.sort(new ServiceUtils.SortByDate());
        return appointments;
    }
}
