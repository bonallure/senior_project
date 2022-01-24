package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dao.AdminDao;
import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dao.DependentDao;
import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.service.AdminServiceLayer;
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
    protected AdultPatientDao adultPatientDao;
    @Autowired
    protected DependentDao dependentDao;

    @Qualifier("systemAdminDaoImpl")
    @Autowired
    protected AdminDao adminDao;

    @Autowired
    protected AdminServiceLayer serviceLayer;

    // Login in a system admin
    @Override
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Admin login(@RequestBody Map admin) {
        SystemAdmin systemAdmin = (SystemAdmin) adminDao.getAdmin((Integer) admin.get("id"));

        if (systemAdmin.getPassword() == ((String) admin.get("password")).hashCode())
            return systemAdmin;
        else
            return null;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String controllerTesting(){
        return "Successfully reaching the Admin Controller.";
    }

    @RequestMapping(value = "/admin/account/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @Override
    public Admin viewAccount(@PathVariable int id) {
        return adminDao.getAdmin(id);
    }

    @RequestMapping(value = "/admin/addDoctor", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Override
    public Doctor addDoctor(@RequestBody Map doctor) throws Exception {
        Doctor newDoctor = new Doctor();
        newDoctor.setEmail((String) doctor.get("email"));
        newDoctor.setFirstName((String) doctor.get("firstName"));
        newDoctor.setLastName((String) doctor.get("lastName"));
        newDoctor.setPassword(doctor.get("password").hashCode());
        newDoctor.setPhoneNumber(Long.valueOf((String) doctor.get("phone")));
        newDoctor.setDOB(Date.valueOf((String) doctor.get("dob")));
        newDoctor.setAddress((String) doctor.get("dob"));
        System.out.println("going the service layer");
        return serviceLayer.saveDoctor(newDoctor);
    }

    @RequestMapping(value = "/admin/adultPatient", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public AdultPatient addPatient(@RequestBody Map adultPatient) {
        AdultPatient patient = new AdultPatient();
        patient.setEmail((String) adultPatient.get("email"));
        patient.setFirstName((String) adultPatient.get("firstName"));
        patient.setLastName((String) adultPatient.get("lastName"));
        patient.setPassword(adultPatient.get("password").hashCode());
        patient.setAddress((String) adultPatient.get("address"));
        patient.setPhoneNumber(Long.valueOf((String) adultPatient.get("phone")));
        patient.setDOB(Date.valueOf((String) adultPatient.get("dob")));
        patient.setSSN(Long.valueOf((String) adultPatient.get("ssn")));
        patient.setDoctorId(Integer.parseInt((String) adultPatient.get("doctorId")));
        return serviceLayer.saveAdultPatient(patient);
    }

    @Override
    public AdultPatient graduatePatient(int dependentId) {
        Patient patient = dependentDao.getDependent(dependentId);
        patient.setId(null);
        return adultPatientDao.addAdultPatient((AdultPatient) patient);
    }
    @RequestMapping(value = "/admin/dependent", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public Dependent addDependent(@RequestBody Map dependent) {
        Dependent patient = new Dependent();
        patient.setEmail((String) dependent.get("email"));
        patient.setFirstName((String) dependent.get("firstName"));
        patient.setLastName((String) dependent.get("lastName"));
        patient.setPassword(dependent.get("password").hashCode());
        patient.setAddress((String) dependent.get("address"));
        patient.setPhoneNumber(Long.valueOf((String) dependent.get("phone")));
        patient.setDOB(Date.valueOf((String) dependent.get("dob")));
        patient.setSSN(Long.valueOf((String) dependent.get("ssn")));
        patient.setDoctorId(Integer.parseInt((String) dependent.get("doctorId")));
        patient.setGuardianId(Integer.parseInt((String) dependent.get("guardianId")));
        return serviceLayer.saveDependent(patient);
    }
}
