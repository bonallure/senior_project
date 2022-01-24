package com.cliniconline.platform.service;

import com.cliniconline.platform.model.dao.*;
import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Dependent;
import com.cliniconline.platform.model.dto.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return doctorDao.addDoctor(doctor);
    }

    public Doctor viewDoctor(int id){
        return doctorDao.getDoctor(id);
    }

    public void editDoctor(Doctor doctor){
        doctorDao.updateDoctor(doctor);
    }

    // TODO
    //  need to create the DTO DAO + IMPLs for user
//    public void deleteDoctor(Doctor){}

    // adultPatient CRUD
    public AdultPatient saveAdultPatient(AdultPatient adultPatient){
        return adultPatientDao.addAdultPatient(adultPatient);
    }

    public AdultPatient viewAdultPatient(int id){
        return adultPatientDao.getPatientById(id);
    }

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

    public void editDependent(Dependent dependent){
        dependentDao.updateDependent(dependent);
    }

    //    public void deleteDependent(Dependent dependent){}

}

