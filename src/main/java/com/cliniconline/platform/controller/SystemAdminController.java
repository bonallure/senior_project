package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dao.AdminDao;
import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dao.DependentDao;
import com.cliniconline.platform.model.dao.DoctorDao;
import com.cliniconline.platform.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

/**
 * Created by bonallure on 10/31/21
 */
@RestController
@CrossOrigin
public class SystemAdminController implements AdminController{

    // Admin Role
    private final Role ROLE = Role.SYSTEM_ADMIN;

    // Wiring the data access objects
    @Autowired
    protected DoctorDao doctorDao;
    @Autowired
    protected AdultPatientDao adultPatientDao;
    @Autowired
    protected DependentDao dependentDao;
    @Qualifier("systemAdminDaoImpl")
    @Autowired
    protected AdminDao adminDao;

    // Login in a system admin
    @Override
    @RequestMapping(value = "/systemAdmin/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Admin login(@RequestBody Map admin) {
        SystemAdmin systemAdmin = (SystemAdmin) adminDao.getAdmin((Integer) admin.get("id"));

        if (systemAdmin.getPassword() == ((String) admin.get("password")).hashCode())
            return systemAdmin;
        else
            return null;
    }

    @RequestMapping(value = "/systemAdmin/account/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Admin viewAccount(@PathVariable int id) {
        return adminDao.getAdmin(id);
    }

    @RequestMapping(value = "/systemAdmin/addDoctor", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Override
    public Doctor addDoctor(@RequestBody Map doctor) {
        Doctor newDoctor = new Doctor();
        newDoctor.setEmail((String) doctor.get("email"));
        newDoctor.setFirstName((String) doctor.get("firstName"));
        newDoctor.setLastName((String) doctor.get("lastName"));
        newDoctor.setPassword(((String) doctor.get("password")).hashCode());
        newDoctor.setPhoneNumber((Long) doctor.get("phone"));
        newDoctor.setDOB((Date) doctor.get("dob"));
        return doctorDao.addDoctor(newDoctor);
    }

    @Override
    public AdultPatient addPatient(Map adultPatient) {
        AdultPatient patient = new AdultPatient();
        patient.setEmail((String) adultPatient.get("email"));
        patient.setFirstName((String) adultPatient.get("firstName"));
        patient.setLastName((String) adultPatient.get("lastName"));
        patient.setPassword(((String) adultPatient.get("password")).hashCode());
        patient.setAddress((String) adultPatient.get("address"));
        patient.setPhoneNumber((Long) adultPatient.get("phone"));
        patient.setDOB((Date) adultPatient.get("dob"));
        patient.setSSN((Integer) adultPatient.get("ssn"));
        patient.setDoctorId((Integer) adultPatient.get("doctorId"));
        return adultPatientDao.addAdultPatient(patient);
    }

    @Override
    public AdultPatient graduatePatient(int dependentId) {
        Patient patient = dependentDao.getDependent(dependentId);
        patient.setId(null);
        return adultPatientDao.addAdultPatient((AdultPatient) patient);
    }

    @Override
    public Dependent addDependent(Map dependent) {
        Dependent patient = new Dependent();
        patient.setEmail((String) dependent.get("email"));
        patient.setFirstName((String) dependent.get("firstName"));
        patient.setLastName((String) dependent.get("lastName"));
        patient.setPassword(((String) dependent.get("password")).hashCode());
        patient.setAddress((String) dependent.get("address"));
        patient.setPhoneNumber((Long) dependent.get("phone"));
        patient.setDOB((Date) dependent.get("dob"));
        patient.setSSN((Integer) dependent.get("ssn"));
        patient.setDoctorId((Integer) dependent.get("doctorId"));
        patient.setGuardianId((Integer) dependent.get("guardianId"));
        return dependentDao.addDependent(patient);
    }
}
