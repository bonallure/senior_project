package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.DoctorDao;
import com.cliniconline.platform.model.dto.Doctor;
import org.springframework.stereotype.Repository;

/**
 * Created by bonallure on 10/20/21
 */
@Repository
public class DoctorDaoImpl implements DoctorDao {

    private static final String INSERT_DOCTOR_SQL =
            "insert in doctor (id, email, f_name, l_name, password, address, phone, dob, role, messages," +
                    "appointments, patients) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_DOCTOR_SQL =
            "select * from doctor where id = ?";

    private static final String SELECT_ALL_DOCTORS_SQL =
            "select * from doctor";

    private static final String DELETE_DOCTOR_SQL =
            "delete from doctor where id = ?";

    private static final String UPDATE_DOCTOR_SQL =
            "update doctor set id = ?, email = ?, f_name = ?, l_name = ?, password = ?, address = ?, phone = ?," +
                    "dob = ?,role = ?, messages = ?, appointments = ?, patients = ?, where id = ?";

    @Override
    public Doctor addDoctor(Doctor dependent) {
        return null;
    }

    @Override
    public void updateDoctor(Doctor dependent) {

    }

    @Override
    public void deleteDoctor(int dependentId) {

    }

    @Override
    public Doctor getDoctor(int dependentId) {
        return null;
    }
}
