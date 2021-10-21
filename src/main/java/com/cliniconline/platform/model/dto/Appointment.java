package com.cliniconline.platform.model.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */

enum AppointmentType {
    VIDEO, PHONE, PHYSICAL
}

public class Appointment {

    private int id;
    private int doctorId;
    private int patientId;
    private Date date;
    private String link;
    private String location = null;
    private AppointmentType type;
    private boolean isConfirmed;
    private String note;
    private Set<Integer> prescriptions;

    public Appointment() {
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

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public List<Date> confirmAppointment(boolean isConfirmed, List<Date> suggestedTimes){
        this.isConfirmed = isConfirmed;
        return suggestedTimes;
    }

    public Set<Integer> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Integer> prescriptions) {
        this.prescriptions.addAll(prescriptions);
    }
}
