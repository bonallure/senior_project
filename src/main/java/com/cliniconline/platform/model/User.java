package com.cliniconline.platform.model;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
public interface User {

    void joinAppointment(Appointment appointment);

    void sendMessage(String message);

    void replyToMessage(String response);

    Set<Message> viewInbox();

    void addToInbox(Long messageID);

    Set<Message> viewOutbox();

    void addToOutbox(Long messageID);

    Set<Appointment> viewAllAppointments();

    void addAppointment(Appointment appointment);

    Set<Prescription> viewPrescriptions();

    User viewAccount();

    Long getId();

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    Address getAddress();

    void setAddress(Address address);

    void changePassword(String password);

    void setPassword(String password);

    int getPhoneNumber();

    void setPhoneNumber(int number);

    Calendar getDOB();
}
