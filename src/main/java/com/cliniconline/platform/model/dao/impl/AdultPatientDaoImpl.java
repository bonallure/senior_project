package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdultPatientDao;
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
 * Created by bonallure on 10/19/21
 */
@Repository
public class AdultPatientDaoImpl implements AdultPatientDao {

    // Prepare statements
    private static final String INSERT_ADULT_PATIEN_SQL =
            "insert into adult_patient (email, f_name, l_name, password, address, phone, dob, ssn, role, doctor_id) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ADULT_PATIENT_BY_ID_SQL =
            "select * from adult_patient where id = ?";

    private static final String SELECT_ADULT_PATIENT_BY_EMAIL_SQL =
            "select * from adult_patient where email = ?";

    private static final String SELECT_ALL_ADULT_PATIENTS_SQL =
            "select * from adult_patient";

    private static final String DELETE_ADULT_PATIENT_SQL =
            "delete from adult_patient where email = ?";

    private static final String UPDATE_ADULT_PATIENT_SQL =
            "update adult_patient set email = ?, f_name = ?, l_name = ?, password = ?, address = ?, phone = ?," +
                    "dob = ?, ssn = ?, role = ?, doctor_id = ? where id = ?";

    private static final String SELECT_ALL_ADULT_PATIENTS_PER_DOCTOR_SQL =
            "select * from adult_patient where doctor_id = ?";

    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AdultPatientDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public AdultPatient getPatient(String email) {
        try {
            return jdbcTemplate.queryForObject(SELECT_ADULT_PATIENT_BY_EMAIL_SQL, this::mapRowToAdultPAtient, email);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public AdultPatient getGuardian(Dependent dependent) {
        try {
            return jdbcTemplate.queryForObject(SELECT_ADULT_PATIENT_BY_ID_SQL, this::mapRowToAdultPAtient,
                    dependent.getGuardianId());
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<AdultPatient> getAllAdultPatient() {
        return jdbcTemplate.query(SELECT_ALL_ADULT_PATIENTS_SQL, this::mapRowToAdultPAtient);
    }

    @Override
    public AdultPatient addAdultPatient(AdultPatient adultPatient) {

        jdbcTemplate.update(INSERT_ADULT_PATIEN_SQL,
                adultPatient.getEmail(),
                adultPatient.getFirstName(),
                adultPatient.getLastName(),
                adultPatient.getPassword(),
                adultPatient.getAddress(),
                adultPatient.getPhoneNumber(),
                adultPatient.getDOB(),
                adultPatient.getSSN(),
                adultPatient.getRole(),
                adultPatient.getDoctorId());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        adultPatient.setId(id);

        return adultPatient;
    }

    @Override
    public void updateAdultPatient(AdultPatient adultPatient) {

        jdbcTemplate.update(UPDATE_ADULT_PATIENT_SQL,
                adultPatient.getEmail(),
                adultPatient.getFirstName(),
                adultPatient.getLastName(),
                adultPatient.getPassword(),
                adultPatient.getAddress(),
                adultPatient.getPhoneNumber(),
                adultPatient.getDOB(),
                adultPatient.getSSN(),
                adultPatient.getRole(),
                adultPatient.getDoctorId(),
                adultPatient.getId());
    }

    @Override
    public void deleteAdultPatient(String email) {

        jdbcTemplate.update(DELETE_ADULT_PATIENT_SQL, email);
    }

    @Override
    public List<AdultPatient> getAllAdultPatientByDoctor(Doctor doctor) {

        return jdbcTemplate.query(SELECT_ALL_ADULT_PATIENTS_PER_DOCTOR_SQL, this::mapRowToAdultPAtient,  doctor.getId());
    }

    // mapToRowAdultPatient
    private AdultPatient mapRowToAdultPAtient(ResultSet rs, int rowNum) throws SQLException {

        AdultPatient adultPatient = new AdultPatient();
        adultPatient.setId(rs.getInt("id"));
        adultPatient.setEmail(rs.getString("email"));
        adultPatient.setFirstName(rs.getString("f_name"));
        adultPatient.setLastName(rs.getString("l_name"));
        adultPatient.setPassword(rs.getString("password"));
        adultPatient.setAddress(rs.getString("address"));
        adultPatient.setPhoneNumber(rs.getInt("phone"));
        adultPatient.setDOB(rs.getDate("dob"));
        adultPatient.setSSN(rs.getInt("ssn"));
        adultPatient.setRole(Role.getRole(rs.getString("role")));
        adultPatient.setDoctorId(rs.getInt("doctor_id"));

        return adultPatient;
    }
}
