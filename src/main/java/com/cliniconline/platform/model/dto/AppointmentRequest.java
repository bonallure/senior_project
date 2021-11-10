package com.cliniconline.platform.model.dto;

import com.cliniconline.platform.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

/**
 * Created by bonallure on 11/6/21
 */
public class AppointmentRequest {

    private int id;
    private int doctorId;
    private int patientId;
    private int appointmentId;
    private Date date;
    private boolean confirmed = false;

    @Autowired
    protected AppointmentDao appointmentDao;

    public AppointmentRequest(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
        if (confirmed){
            Appointment appointment = appointmentDao.getAppointment(this.appointmentId);
            appointment.setConfirmed(true);
            appointmentDao.updateAppointment(appointment);
        }
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppointmentRequest)) return false;

        AppointmentRequest that = (AppointmentRequest) o;

        if (id != that.id) return false;
        if (doctorId != that.doctorId) return false;
        if (patientId != that.patientId) return false;
        if (appointmentId != that.appointmentId) return false;
        if (confirmed != that.confirmed) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + doctorId;
        result = 31 * result + patientId;
        result = 31 * result + appointmentId;
        result = 31 * result + date.hashCode();
        result = 31 * result + (confirmed ? 1 : 0);
        return result;
    }
}
