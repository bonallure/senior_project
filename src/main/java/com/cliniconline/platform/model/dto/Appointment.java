package com.cliniconline.platform.model.dto;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by bonallure on 10/8/21
 */

public class Appointment {

    private int id;
    private int doctorId;
    private int patientId;
    private Date date;
    private Time time;
    private String link;
    private String location = null;
    private AppointmentType type = AppointmentType.VIDEO;
    private boolean isConfirmed;
    private String note;
    private Role requester;

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

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public Role getRequester() {
        return requester;
    }

    public void setRequester(Role requester) {
        this.requester = requester;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
        if (!time.equals(that.time)) return false;
        if (!link.equals(that.link)) return false;
        if (!location.equals(that.location)) return false;
        if (type != that.type) return false;
        if (!note.equals(that.note)) return false;
        return requester == that.requester;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + doctorId;
        result = 31 * result + patientId;
        result = 31 * result + date.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + link.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (isConfirmed ? 1 : 0);
        result = 31 * result + note.hashCode();
        result = 31 * result + requester.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", date=" + date +
                ", time=" + time +
                ", link='" + link + '\'' +
                ", location='" + location + '\'' +
                ", type=" + type +
                ", isConfirmed=" + isConfirmed +
                ", note='" + note + '\'' +
                ", requester=" + requester +
                '}';
    }
}
