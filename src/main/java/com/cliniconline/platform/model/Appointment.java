package com.cliniconline.platform.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by bonallure on 10/8/21
 */

enum AppointmentType {
    VIDEO, PHONE, PHYSICAL
}

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private AdultPatient patient;
    private Calendar date;
    private String link;
    private Address location = null;
    private AppointmentType type;
    private boolean isConfirmed;

    public Appointment() {
    }

    public Appointment(Patient patient, Doctor doctor, Calendar suggestedDateTime){
        this.patient = (AdultPatient) patient;
        this.doctor = doctor;
        this.date = suggestedDateTime;
        this.type = AppointmentType.VIDEO;
        this.isConfirmed = false;
    }

    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public AdultPatient getPatient() {
        return patient;
    }

    public void setPatient(AdultPatient patient) {
        this.patient = patient;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public List<Calendar> confirmAppointment(boolean isConfirmed, List<Calendar> suggestedTimes){
        this.isConfirmed = isConfirmed;
        return suggestedTimes;
    }
}
