package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.DoctorDao;
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
public class DoctorDaoImpl implements DoctorDao {

    private static final String INSERT_DOCTOR_SQL =
            "insert into doctor (email, f_name, l_name, password, address, phone, dob, role) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_DOCTOR_SQL =
            "select * from doctor where id = ?";

    private static final String SELECT_ALL_DOCTORS_SQL =
            "select * from doctor";

    private static final String DELETE_DOCTOR_SQL =
            "delete from doctor where id = ?";

    private static final String UPDATE_DOCTOR_SQL =
            "update doctor set email = ?, f_name = ?, l_name = ?, password = ?, address = ?, phone = ?," +
                    "dob = ?,role = ? where id = ?";

    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Doctor addDoctor(Doctor doctor) {

        jdbcTemplate.update(INSERT_DOCTOR_SQL,
                doctor.getEmail(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getPassword(),
                doctor.getAddress(),
                doctor.getPhoneNumber(),
                doctor.getDOB(),
                doctor.getRole());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        doctor.setId(id);

        return doctor;
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        jdbcTemplate.update(UPDATE_DOCTOR_SQL,
                doctor.getEmail(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getPassword(),
                doctor.getAddress(),
                doctor.getPhoneNumber(),
                doctor.getDOB(),
                doctor.getRole(),
                doctor.getId());
    }

    @Override
    public void deleteDoctor(int doctorId) {
        jdbcTemplate.update(DELETE_DOCTOR_SQL, doctorId);
    }

    @Override
    public Doctor getDoctor(int doctorId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_DOCTOR_SQL, this::mapRowToDoctor, doctorId);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return jdbcTemplate.query(SELECT_ALL_DOCTORS_SQL, this::mapRowToDoctor);
    }

    // mapToRowDoctor
    private Doctor mapRowToDoctor(ResultSet rs, int rowNum) throws SQLException {

        Doctor doctor = new Doctor();
        doctor.setId(rs.getInt("id"));
        doctor.setEmail(rs.getString("email"));
        doctor.setFirstName(rs.getString("f_name"));
        doctor.setLastName(rs.getString("l_name"));
        doctor.setPassword(rs.getString("password"));
        doctor.setPhoneNumber(rs.getInt("phone"));
        doctor.setAddress(rs.getString("address"));
        doctor.setDOB(rs.getDate("dob"));
        doctor.setRole(Role.getRole(rs.getString("role")));

        return doctor;
    }
}
