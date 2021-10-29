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

    List<Message> getAllMessages();

    List<Message> getDoctorInbox(int doctorId);

    List<Message> getPatientInbox(int patientId);

    List<Message> getDoctorOutbox(int doctorId);

    List<Message> getPatientOutbox(int patientId);
}
