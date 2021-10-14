package com.cliniconline.platform.model;

import javax.persistence.*;

/**
 * Created by bonallure on 10/9/21
 */
@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private Long doctorId;
    @ManyToOne
    private Long patientId;
    private String medicine;
    private String dosage;
    private int quantity;
    private boolean refillable;

    public Prescription(Long doctorId, Long patientId, String medicine, String dosage, int quantity, boolean refillable) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medicine = medicine;
        this.dosage = dosage;
        this.quantity = quantity;
        this.refillable = refillable;
    }

    public Prescription() {

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

    public Long getId() {
        return id;
    }
}
