package com.cliniconline.platform.model.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by bonallure on 10/8/21
 */

public class Appointment {

    private int id;
    private int doctorId;
    private int patientId;
    private Date date;
    private String link;
    private String location = null;
    private AppointmentType type = AppointmentType.VIDEO;
    private boolean isConfirmed;
    private String note;

    public Appointment() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type.name();
    }

    public void setType(String type) {
        this.type = AppointmentType.getType(type);
    }

    public List<Date> confirmAppointment(boolean isConfirmed, List<Date> suggestedTimes){
        this.isConfirmed = isConfirmed;
        return suggestedTimes;
    }

    public AppointmentRequest requestAppointment(){
        AppointmentRequest request = new AppointmentRequest();
        request.setAppointmentId(this.id);
        request.setDoctorId(this.doctorId);
        request.setPatientId(this.patientId);
        request.setDate(Date.valueOf(LocalDate.now().toString()));
        return request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;

        Appointment that = (Appointment) o;

        if (id != that.id) return false;
        if (doctorId != that.doctorId) return false;
        if (patientId != that.patientId) return false;
        if (isConfirmed != that.isConfirmed) return false;
        if (!date.equals(that.date)) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (type != that.type) return false;
        return note != null ? note.equals(that.note) : that.note == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + doctorId;
        result = 31 * result + patientId;
        result = 31 * result + date.hashCode();
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + type.hashCode();
        result = 31 * result + (isConfirmed ? 1 : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
