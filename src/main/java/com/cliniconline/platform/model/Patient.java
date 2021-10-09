package com.cliniconline.platform.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class Patient implements User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Object address;
    private int phoneNumber;
    private Date DOB;
    private int SSN;
    private final Role role = Role.PATIENT;

    @ManyToOne
    private Doctor doctor;
    @OneToMany
    private Set<Dependent> dependents;
    @OneToMany
    private Set<Prescription> prescriptions;
    @OneToMany
    private Set<Appointment> appointments;
    @OneToMany
    private Set<Message> messages;

    public Patient() {
    }

    public Patient(String email, String firstName, String lastName, Date DOB, int SSN) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.SSN = SSN;
    }

    void addDependent(String firstName, String lastName, Date DOB, int SSN){
        dependents.add(new Dependent(this.email, firstName, lastName, DOB, SSN));
    }

    @Override
    public void startCall(Call call) {

    }

    @Override
    public void joinCall(Call call) {

    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void replyToMessage(String response) {

    }

    @Override
    public Set<Message> viewMessages() {
        return new HashSet<Message>();
    }

    Set<User> viewDoctors(){
        return null;
    }

    Set<User> viewDependents(){
        return null;
    }

    @Override
    public Set<Appointment> viewAllAppointments() {
        return null;
    }

    @Override
    public Set<Prescription> viewPrescriptions() {
        return null;
    }

    @Override
    public User viewAccount() {
        return null;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getFirstName() {
        return null;
    }


    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void setAddress(Address address) {

    }

    @Override
    public void changePassword(String password) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public int getPhoneNumber() {
        return 0;
    }

    @Override
    public void setPhoneNumber(int number) {

    }

    @Override
    public int getSSN() {
        return 0;
    }

    @Override
    public void setSSN(int ssn) {

    }

    @Override
    public Date getDOB() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (id != null ? !id.equals(patient.id) : patient.id != null) return false;
        if (firstName != null ? !firstName.equals(patient.firstName) : patient.firstName != null) return false;
        if (lastName != null ? !lastName.equals(patient.lastName) : patient.lastName != null) return false;
        if (email != null ? !email.equals(patient.email) : patient.email != null) return false;
        if (password != null ? !password.equals(patient.password) : patient.password != null) return false;
        return role == patient.role;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + role.hashCode();
        return result;
    }
}
