package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.DependentDao;
import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Dependent;
import com.cliniconline.platform.model.dto.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bonallure on 10/20/21
 */

@Repository
public class DependentDaoImpl implements DependentDao {

    // Prepare statements
    private static final String INSERT_DEPENDENT_SQL =
            "insert in dependent (id, email, f_name, l_name, password, address, phone, dob, ssn, role, messages," +
                    "appointments, guardian_id, doctor_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_DEPENDENT_SQL =
            "select * from dependent where id = ?";

    private static final String SELECT_ALL_DEPENDENTS_SQL =
            "select * from dependent";

    private static final String DELETE_DEPENDENT_SQL =
            "delete from dependent where id = ?";

    private static final String UPDATE_DEPENDENT_SQL =
            "update dependent set id = ?, email = ?, f_name = ?, l_name = ?, password = ?, address = ?, phone = ?," +
                    "dob = ?, ssn = ?, role = ?, messages = ?, appointments = ?, guardian_id = ?, doctor_id = ?," +
                    "where id = ?";

    @Override
    public Dependent getDependent(int id) {
        return null;
    }

    @Override
    public List<Dependent> getAllDependentsPerAdultPatient(AdultPatient adultPatient) {
        return null;
    }

    @Override
    public List<Dependent> getAllDependents() {
        return null;
    }

    @Override
    public Dependent addDependent(Dependent dependent) {
        return null;
    }

    @Override
    public void updateDependent(Dependent dependent) {

    }

    @Override
    public void deleteDependent(int id) {

    }

    @Override
    public List<Dependent> getAllDependentByDoctor(Doctor doctor) {
        return null;
    }
}
