package com.cliniconline.platform.model.dto;

import java.time.LocalDate;
import java.sql.Date;
import java.time.Instant;

/**
 * Created by bonallure on 10/9/21
 */

public class Prescription {

    private int id;
    private int doctorId;
    private int patientId;
    private Date date_prescribed;
    private String medicine;
    private String dosage;
    private int quantity;
    private boolean refillable;

    public Prescription() {
    }

    public java.sql.Date getDate_prescribed() {
        return date_prescribed;
    }

    public void setDate_prescribed(Date date_prescribed) {
        this.date_prescribed = date_prescribed;
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

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isRefillable() {
        return refillable;
    }

    public void setRefillable(boolean refillable) {
        this.refillable = refillable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prescription)) return false;

        Prescription that = (Prescription) o;

        if (id != that.id) return false;
        if (doctorId != that.doctorId) return false;
        if (patientId != that.patientId) return false;
        if (quantity != that.quantity) return false;
        if (refillable != that.refillable) return false;
        if (!date_prescribed.equals(that.date_prescribed)) return false;
        if (!medicine.equals(that.medicine)) return false;
        return dosage.equals(that.dosage);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + doctorId;
        result = 31 * result + patientId;
        result = 31 * result + date_prescribed.hashCode();
        result = 31 * result + medicine.hashCode();
        result = 31 * result + dosage.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + (refillable ? 1 : 0);
        return result;
    }
}
