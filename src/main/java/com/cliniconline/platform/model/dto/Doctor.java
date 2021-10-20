package com.cliniconline.platform.model.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class Doctor implements User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private int phoneNumber;
    private Date DOB;
    private final Role role = Role.DOCTOR;
    @OneToMany
    @JoinColumn(name = "MESSAGE_ID")
    private Set<Message> messages = new HashSet<>();
    @OneToMany
    @JoinColumn(name = "APPOINTMENT_ID")
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "PATIENT_ID")
    private Set<AdultPatient> adultPatients = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "PATIENT_ID")
    private Set<Dependent> dependents = new HashSet<>();

    public Doctor() {
    }

    public Doctor(Long id, String firstName, String lastName, String email, String password, String address,
                  int phoneNumber, Date DOB) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Set<AdultPatient> getAdultPatients() {
        return adultPatients;
    }

    public void setAdultPatients(Set<AdultPatient> patients) {
        this.adultPatients.addAll(patients);
    }

    public Set<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(Set<Dependent> dependents) {
        this.dependents.addAll(dependents);
    }

    @Override
    public void changePassword(String password) {
        setAddress(password);
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(int number) {
        phoneNumber = number;
    }

    @Override
    public Date getDOB() {
        return DOB;
    }

    @Override
    public void setRole(Role role) {

    }

    @Override
    public Role getRole() {
        return null;
    }

    @Override
    public Set<Message> getMessages() {
        return messages;
    }

    @Override
    public void setMessages(Set<Message> messages) {
        this.messages.addAll(messages);
    }

    @Override
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments.addAll(appointments);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (id != null ? !id.equals(doctor.id) : doctor.id != null) return false;
        if (firstName != null ? !firstName.equals(doctor.firstName) : doctor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(doctor.lastName) : doctor.lastName != null) return false;
        if (email != null ? !email.equals(doctor.email) : doctor.email != null) return false;
        if (password != null ? !password.equals(doctor.password) : doctor.password != null) return false;
        return role == doctor.role;
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
