package com.cliniconline.platform.service;

import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.model.viewmodel.DoctorViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bonallure on 11/29/21
 */
@Service
public class DoctorServiceLayer implements ServiceLayer{

    // adding the dao
    private DoctorDao doctorDao;
    private AdultPatientDao adultPatientDao;
    private DependentDao dependentDao;
    private MessageDao messageDao;
    private AppointmentDao appointmentDao;
    private PrescriptionDao prescriptionDao;

    @Autowired
    public DoctorServiceLayer(DoctorDao doctorDao, AdultPatientDao adultPatientDao, DependentDao dependentDao,
                               MessageDao messageDao, AppointmentDao appointmentDao, PrescriptionDao prescriptionDao){

        this.doctorDao = doctorDao;
        this.adultPatientDao = adultPatientDao;
        this.dependentDao = dependentDao;
        this.messageDao = messageDao;
        this.appointmentDao = appointmentDao;
        this.prescriptionDao = prescriptionDao;
    }

    public DoctorViewModel findDoctor(int id){

        //Getting the patient
        /*TODO
         *  Allow for adultPatient or dependent selection*/
        Doctor doctor = doctorDao.getDoctor(id);

        return buildPatientViewModel(doctor);
    }

    private DoctorViewModel buildPatientViewModel(Doctor doctor) {

        // getting the service items
        List<Appointment> appointments = sortAppointments(appointmentDao.getAllAppointments());
        List<Message> outbox = messageDao.getDoctorOutbox(doctor.getId());
        List<Message> inbox = messageDao.getDoctorInbox(doctor.getId());
        List<Prescription> prescriptions = prescriptionDao.getAllPrescription();
        List<Patient> patients = new ArrayList<>();

        DoctorViewModel dvm = new DoctorViewModel();
        dvm.setId(doctor.getId());
        dvm.setDoctor(doctor);
        dvm.setEmail(doctor.getEmail());
        dvm.setFirstName(doctor.getFirstName());
        dvm.setLastName((doctor.getLastName()));
        dvm.setRole(doctor.getRole());
        dvm.setAppointments(appointments);
        dvm.setOutbox(outbox);
        dvm.setIntbox(inbox);
        dvm.setPatients(patients);
        dvm.setPrescriptions(prescriptions);

        return dvm;
    }

    private List<Appointment> sortAppointments(List<Appointment> appointments) {
        appointments.sort(new ServiceUtils.SortByDate());
        return appointments;
    }
}
