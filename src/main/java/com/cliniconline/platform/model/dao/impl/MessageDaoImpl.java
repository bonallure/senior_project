package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.MessageDao;
import com.cliniconline.platform.model.dto.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bonallure on 10/20/21
 */
@Repository
public class MessageDaoImpl implements MessageDao {

    private static final String INSERT_MESSAGE_SQL =
            "insert in message (id, recipient_id, sender_id, doctor_id, patient_id, date, parent_message_id, message) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_MESSAGE_SQL =
            "select * from message where id = ?";

    private static final String SELECT_ALL_MESSAGES_SQL =
            "select * from message";

    private static final String DELETE_MESSAGE_SQL =
            "delete from message where id = ?";

    private static final String UPDATE_MESSAGE_SQL =
            "update message set id = ?, recipient_id = ?, sender_id = ?, doctor_id = ?, patient_id = ?, date = ?," +
                    " parent_message_id = ?, message = ?, where id = ?";

    @Override
    public Message addMessage(Message message) {
        return null;
    }

    @Override
    public void updateMessage(Message message) {

    }

    @Override
    public void deleteMessage(int id) {

    }

    @Override
    public Message getMessage(int appointmentId) {
        return null;
    }

    @Override
    public List<Message> getAllMessagesPerPatient(int patientId) {
        return null;
    }

    @Override
    public List<Message> getAllMessagesPerDoctor(int doctorId) {
        return null;
    }
}
