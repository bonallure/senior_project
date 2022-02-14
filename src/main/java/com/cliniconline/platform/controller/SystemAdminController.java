package com.cliniconline.platform.controller;

import com.cliniconline.platform.model.dao.AdminDao;
import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dao.DependentDao;
import com.cliniconline.platform.model.dto.*;
import com.cliniconline.platform.service.AdminServiceLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private final Logger log = LoggerFactory.getLogger(SystemAdminController.class);
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // -----------------------------ADMIN METHODS--------------------------------------
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


    // -----------------------------DOCTOR METHODS--------------------------------------
    // ADD DOCTOR
    @RequestMapping(value = "/admin/addDoctor", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Override
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) throws Exception {
        log.info("Request to create doctor: {}", doctor);
//        Map<String, Object> details = principal.getAttributes();
//        String userId = details.get("sub").toString();

        // check to see if doctor already exists
//        Optional<Doctor> doctorOptional = userRepository.findById(userId);
//        group.setUser(user.orElse(new User(userId,
//                details.get("name").toString(), details.get("email").toString())));

        Doctor result = serviceLayer.saveDoctor(doctor);
        return ResponseEntity.created(new URI("/admin/doctor/" + result.getId()))
                .body(result);
        // ---- old code below
//        Doctor newDoctor = new Doctor();
//        String password = (String) doctor.get("password");
//        String encryptedPassword = passwordEncoder.encode(password);
//
//        newDoctor.setEmail((String) doctor.get("email"));
//        newDoctor.setFirstName((String) doctor.get("firstName"));
//        newDoctor.setLastName((String) doctor.get("lastName"));
//        newDoctor.setPassword(encryptedPassword);
//        newDoctor.setPhoneNumber(Long.valueOf((String) doctor.get("phone")));
//        newDoctor.setDOB(Date.valueOf((String) doctor.get("dob")));
//        newDoctor.setAddress((String) doctor.get("dob"));
//        return serviceLayer.saveDoctor(newDoctor);
    }

    // GET ALL DOCTORS
    @RequestMapping(value = "/admin/doctors", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public List<Doctor> getDoctors() {
        return serviceLayer.viewDoctors();
    }

    // GET DOCTOR
    @RequestMapping(value = "/admin/doctor/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public ResponseEntity<?> getDoctor(@PathVariable int id) {
        Optional<Doctor> doctor = Optional.ofNullable(serviceLayer.viewDoctor(id));
        return doctor.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE DOCTOR
    @RequestMapping(value = "/admin/doctor", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor) throws Exception {
        log.info("Request to update doctor: {}", doctor);
        serviceLayer.editDoctor(doctor);
        Doctor result = serviceLayer.viewDoctor(doctor.getId());
        return ResponseEntity.ok().body(result);
        // old code below
//        Doctor newDoctor = new Doctor();
//        String password = (String) doctor.get("password");
//        String encryptedPassword = passwordEncoder.encode(password);
//
//        newDoctor.setEmail((String) doctor.get("email"));
//        newDoctor.setFirstName((String) doctor.get("firstName"));
//        newDoctor.setLastName((String) doctor.get("lastName"));
//        newDoctor.setPassword(encryptedPassword);
//        newDoctor.setPhoneNumber(Long.valueOf((String) doctor.get("phone")));
//        newDoctor.setDOB(Date.valueOf((String) doctor.get("dob")));
//        newDoctor.setAddress((String) doctor.get("dob"));
//        serviceLayer.editDoctor(newDoctor);
    }

    // DELETE DOCTOR
    @DeleteMapping("/admin/doctor/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable int id){
        log.info("Request to delete doctor: {}", id);
        serviceLayer.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }

    // -----------------------------ADULT PATIENT METHODS--------------------------------------
    @RequestMapping(value = "/admin/adultPatient", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public AdultPatient addPatient(@RequestBody Map adultPatient) {

        AdultPatient patient = new AdultPatient();
        String password = (String) adultPatient.get("password");
        String encryptedPassword = passwordEncoder.encode(password);

        patient.setEmail((String) adultPatient.get("email"));
        patient.setFirstName((String) adultPatient.get("firstName"));
        patient.setLastName((String) adultPatient.get("lastName"));
        patient.setPassword(encryptedPassword);
        patient.setAddress((String) adultPatient.get("address"));
        patient.setPhoneNumber(Long.valueOf((String) adultPatient.get("phone")));
        patient.setDOB(Date.valueOf((String) adultPatient.get("dob")));
        patient.setSSN(Long.valueOf((String) adultPatient.get("ssn")));
        patient.setDoctorId(Integer.parseInt((String) adultPatient.get("doctorId")));
        return serviceLayer.saveAdultPatient(patient);
    }

    @RequestMapping(value = "/admin/adultPatients", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public List<AdultPatient> getAdultPatients() {
        return serviceLayer.viewAdultPatients();
    }


    // -----------------------------DEPENDENT METHODS--------------------------------------
    @RequestMapping(value = "/admin/dependent", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public Dependent addDependent(@RequestBody Map dependent) {

        Dependent patient = new Dependent();
        String password = (String) dependent.get("password");
        String encryptedPassword = passwordEncoder.encode(password);

        patient.setEmail((String) dependent.get("email"));
        patient.setFirstName((String) dependent.get("firstName"));
        patient.setLastName((String) dependent.get("lastName"));
        patient.setPassword(encryptedPassword);
        patient.setAddress((String) dependent.get("address"));
        patient.setPhoneNumber(Long.valueOf((String) dependent.get("phone")));
        patient.setDOB(Date.valueOf((String) dependent.get("dob")));
        patient.setSSN(Long.valueOf((String) dependent.get("ssn")));
        patient.setDoctorId(Integer.parseInt((String) dependent.get("doctorId")));
        patient.setGuardianId(Integer.parseInt((String) dependent.get("guardianId")));
        return serviceLayer.saveDependent(patient);
    }

    @Override
    public AdultPatient graduatePatient(int dependentId) {
        Patient patient = dependentDao.getDependent(dependentId);
        patient.setId(null);
        return adultPatientDao.addAdultPatient((AdultPatient) patient);
    }

    @RequestMapping(value = "/admin/dependents", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public List<Dependent> getDependents() {
        return serviceLayer.viewDependents();
    }


    // -----------------------------PATIENT METHODS--------------------------------------
    @RequestMapping(value = "/admin/patients", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Override
    public List<Patient> getPatients() {
        List<AdultPatient> adultPatients = this.getAdultPatients();
        List<Dependent> dependents = this.getDependents();
        List<Patient> patients = new ArrayList<>(adultPatients.size()+dependents.size());

        for (AdultPatient adultPatient : adultPatients) {
            patients.add((Patient) adultPatient);
        }

        for (Dependent dependent : dependents) {
            patients.add((Patient) dependent);
        }
        return patients;
    }

}
