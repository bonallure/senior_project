package com.cliniconline.platform.model.dto;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long recipientId;
    private Long senderId;
    private String message;
    private Calendar dateTime;
    private Long parent = null;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private AdultPatient patient;

    public Message() {

    }

    public Message(Long senderId, Long recipientId, String message){
        this.dateTime = Calendar.getInstance();
        this.message = message;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public void sendMessage(){
    }

    public Message reply(Message originalMessage, String text){
        Message newMessage = new Message(originalMessage.recipientId, originalMessage.senderId, text);
        newMessage.parent = originalMessage.id;
        // TODO
        return newMessage;
    }

    public Long getId() {
        return id;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public AdultPatient getPatient() {
        return patient;
    }

    public void setPatient(AdultPatient adultPatient) {
        this.patient = adultPatient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
}

