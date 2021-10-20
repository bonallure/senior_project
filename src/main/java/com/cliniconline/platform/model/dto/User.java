package com.cliniconline.platform.model.dto;

import java.sql.Date;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
public interface User {

    Long getId();

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getAddress();

    void setAddress(String address);

    void changePassword(String password);

    void setPassword(String password);

    int getPhoneNumber();

    void setPhoneNumber(int number);

    Date getDOB();

    void setRole(Role role);

    Role getRole();

    Set<Message> getMessages();

    void setMessages(Set<Message> messages);

    Set<Appointment> getAppointments();

    void setAppointments(Set<Appointment> appointments);
}