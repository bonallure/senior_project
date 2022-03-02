package com.cliniconline.platform.model.dto;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.sql.Date;

/**
 * Created by bonallure on 10/8/21
 */

public class Message {

    private int id;
    private Role recipientRole;
    private Role senderRole;
    private String message;
    private Date date;
    private LocalTime time;
    private int parentMessageId;
    private int doctorId;
    private int patientId;

    public Message() {
    }

    public Message reply(String text){
        Message newMessage = new Message();

        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        newMessage.setTime(new Time(now.getTime()));
        now.setTime(0);
        newMessage.setDate(now);

        newMessage.setMessage(text);
        newMessage.parentMessageId = this.getParentMessageId();
        newMessage.setSenderRole(this.getRecipientRole());
        newMessage.setRecipientRole(this.getSenderRole());
        newMessage.setDoctorId(this.doctorId);
        newMessage.setPatientId(this.patientId);
        return newMessage;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = LocalTime.parse(time.toString());
    }

    public int getId() {
        return id;
    }

    public String getRecipientRole() {
        return recipientRole.name();
    }

    public void setRecipientRole(String role) {
        this.recipientRole = Role.getRole(role);
    }

    public String getSenderRole() {
        return senderRole.name();
    }

    public void setSenderRole(String role) {
        this.senderRole = Role.getRole(role);
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getParentMessageId() {
        return parentMessageId;
    }

    public void setParentMessageId(int parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message1 = (Message) o;

        if (id != message1.id) return false;
        if (parentMessageId != message1.parentMessageId) return false;
        if (doctorId != message1.doctorId) return false;
        if (patientId != message1.patientId) return false;
        if (recipientRole != message1.recipientRole) return false;
        if (senderRole != message1.senderRole) return false;
        if (!message.equals(message1.message)) return false;
        if (!(date.compareTo(message1.date) == 0)) return false;
        return (time.compareTo(message1.time) == 0);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + recipientRole.hashCode();
        result = 31 * result + senderRole.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + date.toString().hashCode();
        result = 31 * result + time.toString().hashCode();
        result = 31 * result + parentMessageId;
        result = 31 * result + doctorId;
        result = 31 * result + patientId;
        return result;
    }
}

