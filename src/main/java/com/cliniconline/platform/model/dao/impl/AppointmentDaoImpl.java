package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AppointmentDao;
import com.cliniconline.platform.model.dto.Appointment;
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
public class AppointmentDaoImpl implements AppointmentDao {

    private static final String INSERT_APPOINTMENT_SQL =
            "insert into appointment (doctor_id, patient_id, date, link, location, type, confirmed, note) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_APPOINTMENT_SQL =
            "select * from appointment where id = ?";

    private static final String SELECT_ALL_APPOINTMENTS_SQL =
            "select * from appointment";

    private static final String DELETE_APPOINTMENT_SQL =
            "delete from appointment where id = ?";

    private static final String UPDATE_APPOINTMENT_SQL =
            "update appointment set doctor_id = ?, patient_id = ?, date = ?, link = ?, location = ?, type = ?, " +
                    "confirmed = ?, note = ? where id = ?";

    private static final String SELECT_ALL_APPOINTMENTS_PER_PATIENT_SQL =
            "select * from appointment where patient_id = ?";

    private static final String SELECT_ALL_APPOINTMENTS_PER_DOCTOR_SQL =
            "select * from appointment where doctor_id = ?";

    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AppointmentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {

        jdbcTemplate.update(INSERT_APPOINTMENT_SQL,
                appointment.getDoctorId(),
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getLink(),
                appointment.getLocation(),
                appointment.getType(),
                appointment.isConfirmed(),
                appointment.getNote());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        appointment.setId(id);

        return appointment;
    }

    @Override
    public void updateAppointment(Appointment appointment) {

        jdbcTemplate.update(UPDATE_APPOINTMENT_SQL,
                appointment.getDoctorId(),
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getLink(),
                appointment.getLocation(),
                appointment.getType(),
                appointment.isConfirmed(),
                appointment.getNote(),
                appointment.getId());
    }

    @Override
    public void deleteAppointment(int appointmentId) {

        jdbcTemplate.update(DELETE_APPOINTMENT_SQL, appointmentId);
    }

    @Override
    public Appointment getAppointment(int appointmentId) {
        try
        {
            return jdbcTemplate.queryForObject(SELECT_APPOINTMENT_SQL, this::mapToRowAppointment, appointmentId);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Appointment> getAllAppointmentsPerPatient(int patientId) {

        return jdbcTemplate.query(SELECT_ALL_APPOINTMENTS_PER_PATIENT_SQL, this::mapToRowAppointment, patientId);
    }

    @Override
    public List<Appointment> getAllAppointmentsPerDoctor(int doctorId) {

        return jdbcTemplate.query(SELECT_ALL_APPOINTMENTS_PER_DOCTOR_SQL, this::mapToRowAppointment, doctorId);
    }

    @Override
    public List<Appointment> getAllAppointments() {

        return jdbcTemplate.query(SELECT_ALL_APPOINTMENTS_SQL, this::mapToRowAppointment);
    }

    // mapToRowAppointment
    private Appointment mapToRowAppointment(ResultSet rs, int rowNum) throws SQLException {

        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("id"));
        appointment.setDoctorId(rs.getInt("doctor_id"));
        appointment.setPatientId(rs.getInt("patient_id"));
        appointment.setDate(rs.getDate("date"));
        appointment.setLink(rs.getString("link"));
        appointment.setLocation(rs.getString("location"));
        appointment.setType(rs.getString("type"));
        appointment.setConfirmed(rs.getBoolean("confirmed"));
        appointment.setNote(rs.getString("note"));

        return appointment;
    }
}
