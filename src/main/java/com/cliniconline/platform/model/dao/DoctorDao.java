package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by bonallure on 10/14/21
 */
public interface DoctorDao {

    Doctor addDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    void deleteDoctor(int doctorId);

    Doctor getDoctor(int doctorId);

    Doctor getDoctorByEmail(String email);

    List<Doctor> getAllDoctors();
}
