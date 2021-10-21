package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.DependentDao;
import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Dependent;
import com.cliniconline.platform.model.dto.Doctor;
import com.cliniconline.platform.model.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bonallure on 10/20/21
 */

@Repository
public class DependentDaoImpl implements DependentDao {

    // Prepare statements
    private static final String INSERT_DEPENDENT_SQL =
            "insert into dependent (email, f_name, l_name, password, address, phone, dob, ssn, role, guardian_id," +
                    " doctor_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_DEPENDENT_SQL =
            "select * from dependent where id = ?";

    private static final String SELECT_ALL_DEPENDENTS_SQL =
            "select * from dependent";

    private static final String DELETE_DEPENDENT_SQL =
            "delete from dependent where id = ?";

    private static final String UPDATE_DEPENDENT_SQL =
            "update dependent set email = ?, f_name = ?, l_name = ?, password = ?, address = ?, phone = ?," +
                    "dob = ?, ssn = ?, role = ?, guardian_id = ?, doctor_id = ? where id = ?";

    private static final String SELECT_ALL_DEPENDENTS_PER_DOCTOR_SQL =
            "select * from dependent where doctor_id = ?";

    private static final String SELECT_ALL_DEPENDENTS_PER_ADULT_PATIENT_SQL =
            "select * from dependent where guardian_id = ?";

    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DependentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dependent getDependent(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_DEPENDENT_SQL, this::mapRowToDependent, id);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Dependent> getAllDependentsPerAdultPatient(AdultPatient adultPatient) {

        return jdbcTemplate.query(SELECT_ALL_DEPENDENTS_PER_ADULT_PATIENT_SQL, this::mapRowToDependent,
                adultPatient.getId());
    }

    @Override
    public List<Dependent> getAllDependents() {

        return jdbcTemplate.query(SELECT_ALL_DEPENDENTS_SQL, this::mapRowToDependent);
    }

    @Override
    public Dependent addDependent(Dependent dependent) {

        jdbcTemplate.update(INSERT_DEPENDENT_SQL,
                dependent.getEmail(),
                dependent.getFirstName(),
                dependent.getLastName(),
                dependent.getPassword(),
                dependent.getAddress(),
                dependent.getPhoneNumber(),
                dependent.getDOB(),
                dependent.getSSN(),
                dependent.getRole(),
                dependent.getGuardianId(),
                dependent.getDoctorId());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        dependent.setId(id);

        return dependent;
    }

    @Override
    public void updateDependent(Dependent dependent) {

        jdbcTemplate.update(UPDATE_DEPENDENT_SQL,
                dependent.getEmail(),
                dependent.getFirstName(),
                dependent.getLastName(),
                dependent.getPassword(),
                dependent.getAddress(),
                dependent.getPhoneNumber(),
                dependent.getDOB(),
                dependent.getSSN(),
                dependent.getRole(),
                dependent.getGuardianId(),
                dependent.getDoctorId(),
                dependent.getId());
    }

    @Override
    public void deleteDependent(int id) {

        jdbcTemplate.update(DELETE_DEPENDENT_SQL, id);
    }

    @Override
    public List<Dependent> getAllDependentByDoctor(Doctor doctor) {

        return jdbcTemplate.query(SELECT_ALL_DEPENDENTS_PER_DOCTOR_SQL,this::mapRowToDependent, doctor.getId());
    }

    // mapToRowAdultPatient
    private Dependent mapRowToDependent(ResultSet rs, int rowNum) throws SQLException {

        Dependent dependent = new Dependent();
        dependent.setId(rs.getInt("id"));
        dependent.setEmail(rs.getString("email"));
        dependent.setFirstName(rs.getString("f_name"));
        dependent.setLastName(rs.getString("l_name"));
        dependent.setPassword(rs.getString("password"));
        dependent.setAddress(rs.getString("address"));
        dependent.setPhoneNumber(rs.getInt("phone"));
        dependent.setDOB(rs.getDate("dob"));
        dependent.setSSN(rs.getInt("ssn"));
        dependent.setRole(Role.getRole(rs.getString("role")));
        dependent.setGuardianId(rs.getInt("guardian_id"));
        dependent.setDoctorId(rs.getInt("doctor_id"));

        return dependent;
    }
}
