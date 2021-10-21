package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Dependent;
import com.cliniconline.platform.model.dto.Doctor;

import java.util.List;

/**
 * Created by bonallure on 10/14/21
 */

public interface DoctorDao {

    Doctor addDoctor(Doctor dependent);

    void updateDoctor(Doctor dependent);

    void deleteDoctor(int dependentId);

    Doctor getDoctor(int dependentId);
}
