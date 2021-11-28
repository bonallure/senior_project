package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.PrescriptionDao;
import com.cliniconline.platform.model.dto.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bonallure on 10/20/21
 */
@Repository
public class PrescriptionDaoImpl implements PrescriptionDao {

    private static final String INSERT_PRESCRIPTION_SQL =
            "insert into prescription (doctor_id, patient_id, date, medicine, dosage, quantity, refillable) values" +
                    " (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_PRESCRIPTION_SQL =
            "select * from prescription where id = ?";

    private static final String SELECT_ALL_PRESCRIPTIONS_SQL =
            "select * from prescription";

    private static final String SELECT_ALL_PRESCRIPTIONS_WHERE_PATIENT_ID_SQL =
            "select * from prescription where patient_id = ?";

    private static final String SELECT_ALL_PRESCRIPTIONS_WHERE_DOCTOR_ID_SQL =
            "select * from prescription where doctor_id = ?";

    private static final String DELETE_PRESCRIPTION_SQL =
            "delete from prescription where id = ?";

    private static final String UPDATE_PRESCRIPTION_SQL =
            "update prescription set doctor_id = ?, patient_id = ?, date = ?, medicine = ?, dosage = ?," +
                    " quantity = ?, refillable = ? where id = ?";


    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PrescriptionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Prescription addPrescription(Prescription prescription) {

        jdbcTemplate.update(INSERT_PRESCRIPTION_SQL,
                prescription.getDoctorId(),
                prescription.getPatientId(),
                prescription.getDate_prescribed(),
                prescription.getMedicine(),
                prescription.getDosage(),
                prescription.getQuantity(),
                prescription.isRefillable());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        prescription.setId(id);

        return prescription;
    }

    @Override
    public void updatePrescription(Prescription prescription) {

        jdbcTemplate.update(UPDATE_PRESCRIPTION_SQL,
                prescription.getDoctorId(),
                prescription.getPatientId(),
                prescription.getDate_prescribed(),
                prescription.getMedicine(),
                prescription.getDosage(),
                prescription.getQuantity(),
                prescription.isRefillable(),
                prescription.getId());
    }

    @Override
    public void deletePrescription(int prescriptionId) {

        jdbcTemplate.update(DELETE_PRESCRIPTION_SQL, prescriptionId);
    }

    @Override
    public Prescription getPrescription(int prescriptionId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PRESCRIPTION_SQL, this::mapToRowPrescription, prescriptionId);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Prescription> getAllPrescription() {

        return jdbcTemplate.query(SELECT_ALL_PRESCRIPTIONS_SQL, this::mapToRowPrescription);
    }

    @Override
    public List<Prescription> getAllPrescriptionsPerPatient(int patientId) {

        return jdbcTemplate.query(SELECT_ALL_PRESCRIPTIONS_WHERE_PATIENT_ID_SQL, this::mapToRowPrescription, patientId);
    }

    @Override
    public List<Prescription> getAllPrescriptionsPerDoctor(int doctorId) {

        return jdbcTemplate.query(SELECT_ALL_PRESCRIPTIONS_WHERE_DOCTOR_ID_SQL, this::mapToRowPrescription, doctorId);
    }

    /*
    * id int not null auto_increment primary key,
    doctor_id int not null,
    patient_id int not null,
    date date not null,
    medicine varchar(20),
    dosage varchar(20),
    quantity int not null,
    refillable boolean not null*/

    // mapToRowPrescription
    private Prescription mapToRowPrescription(ResultSet rs, int rowNum) throws SQLException{

        Prescription prescription = new Prescription();
        prescription.setId(rs.getInt("id"));
        prescription.setDoctorId(rs.getInt("doctor_id"));
        prescription.setPatientId(rs.getInt("patient_id"));
        prescription.setDate_prescribed(rs.getDate("date"));
        prescription.setMedicine(rs.getString("medicine"));
        prescription.setDosage(rs.getString("dosage"));
        prescription.setQuantity(rs.getInt("quantity"));
        prescription.setRefillable(rs.getBoolean("refillable"));

        return prescription;
    }
}
