package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.Appointment;
import java.util.List;

/**
 * Created by bonallure on 10/14/21
 */

public interface AppointmentDao {

    Appointment addAppointment(Appointment appointment);

    void updateAppointment(Appointment appointment);

    void deleteAppointment(int appointmentId);

    Appointment getAppointment(int appointmentId);

    List<Appointment> getAllAppointmentsPerPatient(int patientId);

    List<Appointment> getAllAppointmentsPerDoctor(int doctorId);

    List<Appointment> getAllAppointments();
}
