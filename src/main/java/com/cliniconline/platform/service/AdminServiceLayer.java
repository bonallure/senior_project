package com.cliniconline.platform.service;

import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Dependent;
import com.cliniconline.platform.model.dto.Doctor;
import com.cliniconline.platform.model.dto.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bonallure on 1/23/22
 */

@Service
public class AdminServiceLayer {

    // adding the dao
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
    protected PasswordEncoder passwordEncoder;

    // doctor CRUD
    public Doctor saveDoctor(Doctor doctor) throws Exception{
        String encryptedPassword = passwordEncoder.encode(doctor.getPassword());
        return doctorDao.addDoctor(doctor);
    }
    public Doctor viewDoctor(int id){
        return doctorDao.getDoctor(id);
    }

    public List<Doctor> viewDoctors() { return doctorDao.getAllDoctors(); }

    public void editDoctor(Doctor doctor){
        doctorDao.updateDoctor(doctor);
    }

    // TODO
    //  need to create the DTO DAO + IMPLs for user
    public void deleteDoctor(int id){
        doctorDao.deleteDoctor(id);
    }

    // adultPatient CRUD
    public AdultPatient saveAdultPatient(AdultPatient adultPatient){
        String encryptedPassword = passwordEncoder.encode(adultPatient.getPassword());
        return adultPatientDao.addAdultPatient(adultPatient);
    }

    public AdultPatient viewAdultPatient(int id){
        return adultPatientDao.getPatientById(id);
    }

    public List<AdultPatient> viewAdultPatients() { return adultPatientDao.getAllAdultPatient(); }

    public void editAdultPatient(AdultPatient adultPatient){
        adultPatientDao.updateAdultPatient(adultPatient);
    }

    //    public void deleteAdultPatient(AdultPatient adultPatient){}

    // dependent CRUD
    public Dependent saveDependent(Dependent dependent){
        return dependentDao.addDependent(dependent);
    }

    public Dependent viewDependent(int id){
        return dependentDao.getDependent(id);
    }

    public List<Dependent> viewDependents() { return dependentDao.getAllDependents(); }

    public void editDependent(Dependent dependent){
        dependentDao.updateDependent(dependent);
    }

    //    public void deleteDependent(Dependent dependent){}

}

