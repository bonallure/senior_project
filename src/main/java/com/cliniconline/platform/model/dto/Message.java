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
    private boolean read = false;

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

    public void setRecipientRole(Role recipientRole) {
        this.recipientRole = recipientRole;
    }

    public void setSenderRole(Role senderRole) {
        this.senderRole = senderRole;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
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
        if (read != message1.read) return false;
        if (recipientRole != message1.recipientRole) return false;
        if (senderRole != message1.senderRole) return false;
        if (!message.equals(message1.message)) return false;
        if (!date.equals(message1.date)) return false;
        return time.equals(message1.time);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + recipientRole.hashCode();
        result = 31 * result + senderRole.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + parentMessageId;
        result = 31 * result + doctorId;
        result = 31 * result + patientId;
        result = 31 * result + (read ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", recipientRole=" + recipientRole +
                ", senderRole=" + senderRole +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", parentMessageId=" + parentMessageId +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", read=" + read +
                '}';
    }
}

