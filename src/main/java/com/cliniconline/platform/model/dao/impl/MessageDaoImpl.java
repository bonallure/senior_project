package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.MessageDao;
import com.cliniconline.platform.model.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by bonallure on 10/20/21
 */
@Repository
public class MessageDaoImpl implements MessageDao {

    private static final String INSERT_MESSAGE_SQL =
            "insert into message (recipient_role, sender_role, doctor_id, patient_id, date, time, parent_message_id, message) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_MESSAGE_SQL =
            "select * from message where id = ?";

    private static final String SELECT_ALL_MESSAGES_SQL =
            "select * from message";

    private static final String SELECT_FROM_MESSAGE_WHERE_SENDER_ROLE_DOCTOR =
            "select * from message where sender_role = 'DOCTOR' and doctor_id = ?";

    private static final String SELECT_FROM_MESSAGE_WHERE_RECIPIENT_ROLE_DOCTOR =
            "select * from message where recipient_role = 'DOCTOR' and doctor_id = ?";

    private static final String SELECT_FROM_MESSAGE_WHERE_SENDER_ROLE_PATIENT =
            "select * from message where sender_role = 'PATIENT' and patient_id = ?";

    private static final String SELECT_FROM_MESSAGE_WHERE_RECIPIENT_ROLE_PATIENT =
            "select * from message where recipient_role = 'PATIENT' and patient_id = ?";

    private static final String DELETE_MESSAGE_SQL =
            "delete from message where id = ?";

    private static final String UPDATE_MESSAGE_SQL =
            "update message set recipient_role = ?, sender_role = ?, doctor_id = ?, patient_id = ?, date = ?, time = ?," +
                    " parent_message_id = ?, message = ? where id = ?";


    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Message addMessage(Message message) {

        jdbcTemplate.update(INSERT_MESSAGE_SQL,
                message.getRecipientRole(),
                message.getSenderRole(),
                message.getDoctorId(),
                message.getPatientId(),
                message.getDate(),
                message.getTime(),
                message.getParentMessageId(),
                message.getMessage());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        message.setId(id);

        return message;
    }

    @Override
    public void updateMessage(Message message) {

        jdbcTemplate.update(UPDATE_MESSAGE_SQL,
                message.getRecipientRole(),
                message.getSenderRole(),
                message.getDoctorId(),
                message.getPatientId(),
                message.getDate(),
                message.getTime(),
                message.getParentMessageId(),
                message.getMessage(),
                message.getId());
    }

    @Override
    public void deleteMessage(int id) {

        jdbcTemplate.update(DELETE_MESSAGE_SQL, id);
    }

    @Override
    public Message getMessage(int appointmentId) {
        try
        {
            return jdbcTemplate.queryForObject(SELECT_MESSAGE_SQL, this::mapToRowMessage, appointmentId);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Message> getAllMessages() {

        return jdbcTemplate.query(SELECT_ALL_MESSAGES_SQL, this::mapToRowMessage);
    }

    @Override
    public List<Message> getDoctorInbox(int doctorId) {

        return jdbcTemplate.query(SELECT_FROM_MESSAGE_WHERE_RECIPIENT_ROLE_DOCTOR, this::mapToRowMessage, doctorId);
    }

    @Override
    public List<Message> getPatientInbox(int patientId) {

        return jdbcTemplate.query(SELECT_FROM_MESSAGE_WHERE_RECIPIENT_ROLE_PATIENT, this::mapToRowMessage, patientId);
    }

    @Override
    public List<Message> getDoctorOutbox(int doctorId) {
        return jdbcTemplate.query(SELECT_FROM_MESSAGE_WHERE_SENDER_ROLE_DOCTOR, this::mapToRowMessage, doctorId);
    }

    @Override
    public List<Message> getPatientOutbox(int patientId) {
        return jdbcTemplate.query(SELECT_FROM_MESSAGE_WHERE_SENDER_ROLE_PATIENT, this::mapToRowMessage, patientId); }

    // mapToRowMessage
    private Message mapToRowMessage(ResultSet rs, int rowNum) throws SQLException {

        Message message = new Message();
        message.setId(rs.getInt("id"));
        message.setRecipientRole(rs.getString("recipient_role"));
        message.setSenderRole((rs.getString("sender_role")));
        message.setDoctorId(rs.getInt("doctor_id"));
        message.setPatientId(rs.getInt("patient_id"));
        message.setDate(rs.getDate("date"));
        message.setTime(rs.getTime("time", new GregorianCalendar(TimeZone.getTimeZone("GMT-6:00"))));
        message.setParentMessageId(rs.getInt("parent_message_id"));
        message.setMessage(rs.getString("message"));

        return message;
    }
}
