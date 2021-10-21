package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AppointmentDao;
import com.cliniconline.platform.model.dto.Appointment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bonallure on 10/20/21
 */
@Repository
public class AppointmentDaoImpl implements AppointmentDao {

    private static final String INSERT_APPOINTMENT_SQL =
            "insert in appointment (id, doctor_id, patient_id, date, link, location, type, confirmed, notes, " +
                    "prescriptions) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_APPOINTMENT_SQL =
            "select * from appointment where id = ?";

    private static final String SELECT_ALL_APPOINTMENTS_SQL =
            "select * from appointment";

    private static final String DELETE_APPOINTMENT_SQL =
            "delete from appointment where id = ?";

    private static final String UPDATE_APPOINTMENT_SQL =
            "update appointment set id = ?, doctor_id = ?, patient_id = ?, date = ?, link = ?, location = ?, type = ?, " +
                    "confirmed = ?, notes = ?, prescriptions = ?, where id = ?";

    @Override
    public Appointment addAppointment(Appointment appointment) {
        return null;
    }

    @Override
    public void updateAppointment(Appointment appointment) {

    }

    @Override
    public void deleteAppointment(int appointmentId) {

    }

    @Override
    public Appointment getAppointment(int appointmentId) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointmentsPerPatient(int patientId) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointmentsPerDoctor(int doctorId) {
        return null;
    }
}
