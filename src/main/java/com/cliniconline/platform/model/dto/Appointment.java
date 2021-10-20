package com.cliniconline.platform.model.dto;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

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
    private Long doctorId;
    @ManyToOne
    private Long patientId;
    private Calendar date;
    private String link;
    private Address location = null;
    private AppointmentType type;
    private boolean isConfirmed;
    private String note;
    private Set<Long> prescriptions;

    public Appointment() {
    }

    public Appointment(Long patientId, Long doctorId, Calendar suggestedDateTime){
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = suggestedDateTime;
        this.type = AppointmentType.VIDEO;
        this.isConfirmed = false;
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

    public Long getId() {
        return id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public Set<Long> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Long> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
