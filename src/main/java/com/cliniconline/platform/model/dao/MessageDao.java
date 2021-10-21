package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.Message;

import java.util.List;

/**
 * Created by bonallure on 10/14/21
 */

public interface MessageDao {

    Message addMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(int id);

    Message getMessage(int appointmentId);

    List<Message> getAllMessagesPerPatient(int patientId);

    List<Message> getAllMessagesPerDoctor(int doctorId);
}
