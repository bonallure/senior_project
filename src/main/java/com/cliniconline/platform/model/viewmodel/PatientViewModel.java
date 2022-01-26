package com.cliniconline.platform.model.viewmodel;

import com.cliniconline.platform.model.dto.Appointment;
import com.cliniconline.platform.model.dto.Doctor;
import com.cliniconline.platform.model.dto.Message;
import com.cliniconline.platform.model.dto.Prescription;

import java.util.List;

/**
 * Created by bonallure on 11/10/21
 */
// This class contains all attributes related to a patient for viewing
public class PatientViewModel implements UserViewModel{

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private List<Appointment> appointments;
    private List<Message> outbox;
    private List<Message> inbox;
    private List<Doctor> doctors;
    private List<Prescription> prescriptions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Message> getOutbox() {
        return outbox;
    }

    public void setOutbox(List<Message> messages) {
        this.outbox = messages;
    }

    public List<Message> getIntbox() {
        return inbox;
    }

    public void setIntbox(List<Message> messages) {
        this.inbox = messages;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientViewModel)) return false;

        PatientViewModel that = (PatientViewModel) o;

        if (id != that.id) return false;
        if (!email.equals(that.email)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!role.equals(that.role)) return false;
        if (!appointments.equals(that.appointments)) return false;
        if (!inbox.equals(that.inbox)) return false;
        if (!outbox.equals(that.outbox)) return false;
        if (!doctors.equals(that.doctors)) return false;
        return prescriptions.equals(that.prescriptions);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + appointments.hashCode();
        result = 31 * result + inbox.hashCode();
        result = 31 * result + outbox.hashCode();
        result = 31 * result + doctors.hashCode();
        result = 31 * result + prescriptions.hashCode();
        return result;
    }
}
