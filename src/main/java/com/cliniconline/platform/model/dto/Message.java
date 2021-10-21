package com.cliniconline.platform.model.dto;

import java.util.Calendar;
import java.sql.Date;

/**
 * Created by bonallure on 10/8/21
 */

public class Message {

    private int id;
    private int recipientId;
    private int senderId;
    private String message;
    private Date dateTime;
    private Message parentMessage;
    private Doctor doctor;
    private Patient patient;

    public Message() {
    }

    public void sendMessage(){
    }

    public Message reply(Message originalMessage, String text){
        Message newMessage = new Message();
        newMessage.setMessage(text);
        newMessage.setDateTime(new Date(Calendar.getInstance().getTimeInMillis()));
        newMessage.parentMessage = originalMessage.getParentMessage();
        // TODO
        return newMessage;
    }

    public int getId() {
        return id;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Message getParentMessage() {
        return parentMessage;
    }

    public void setParentMessage(Message parentMessage) {
        this.parentMessage = parentMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (id != message1.id) return false;
        if (recipientId != message1.recipientId) return false;
        if (senderId != message1.senderId) return false;
        if (!message.equals(message1.message)) return false;
        if (!dateTime.equals(message1.dateTime)) return false;
        if (parentMessage != null ? !parentMessage.equals(message1.parentMessage) : message1.parentMessage != null)
            return false;
        if (!doctor.equals(message1.doctor)) return false;
        return patient.equals(message1.patient);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + recipientId;
        result = 31 * result + senderId;
        result = 31 * result + message.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + (parentMessage != null ? parentMessage.hashCode() : 0);
        result = 31 * result + doctor.hashCode();
        result = 31 * result + patient.hashCode();
        return result;
    }
}

