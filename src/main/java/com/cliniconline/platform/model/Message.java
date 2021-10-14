package com.cliniconline.platform.model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private User recipient;
    private User sender;
    private String message;
    private Calendar dateTime;
    private Long parent = null;

    public Message() {

    }

    public Message(User sender, User recipient, String message){
        this.dateTime = Calendar.getInstance();
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Message sendMessage(){
        this.recipient.addToInbox(this.id);
        this.sender.addToOutbox(this.id);
        return this;
    }

    public Message reply(Message originalMessage, String text){
        Message newMessage = new Message(originalMessage.recipient, originalMessage.sender, text);
        newMessage.parent = originalMessage.id;
        newMessage.recipient.addToInbox(newMessage.id);
        newMessage.sender.addToOutbox(newMessage.id);
        return newMessage;
    }

    public Long getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
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

