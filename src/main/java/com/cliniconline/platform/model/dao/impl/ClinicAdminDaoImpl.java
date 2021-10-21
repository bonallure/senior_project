package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdminDao;
import com.cliniconline.platform.model.dto.Admin;
import com.cliniconline.platform.model.dto.ClinicAdmin;
import com.cliniconline.platform.model.dto.SystemAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bonallure on 10/21/21
 */
@Repository
public class ClinicAdminDaoImpl implements AdminDao {

    // Prepare statements
    private static final String INSERT_CLINIC_ADMIN_SQL =
            "insert into clinic_admin (email, f_name, l_name, password, role) values (?, ?, ?, ?, ?)";

    private static final String SELECT_CLINIC_ADMIN_SQL =
            "select * from clinic_admin where id = ?";

    private static final String SELECT_ALL_CLINIC_ADMINS_SQL =
            "select * from clinic_admin";

    private static final String DELETE_CLINIC_ADMIN_SQL =
            "delete from clinic_admin where id = ?";

    private static final String UPDATE_CLINIC_ADMIN_SQL =
            "update clinic_admin set email = ?, f_name = ?, l_name = ?, password = ?, role = ? where id = ?";

    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ClinicAdminDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Admin getAdmin(int id) {

        try {
            return jdbcTemplate.queryForObject(SELECT_CLINIC_ADMIN_SQL, this::mapRowToClinicAdmin, id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Admin> getAllAdmin() {

        return jdbcTemplate.query(SELECT_ALL_CLINIC_ADMINS_SQL, this::mapRowToClinicAdmin);
    }

    @Override
    public Admin addAdmin(Admin admin) {

        jdbcTemplate.update(INSERT_CLINIC_ADMIN_SQL,
                admin.getEmail(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getPassword(),
                admin.getRole());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        admin.setId(id);

        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {

        jdbcTemplate.update(UPDATE_CLINIC_ADMIN_SQL,
                admin.getEmail(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getPassword(),
                admin.getRole(),
                admin.getId());
    }

    @Override
    public void deleteAdmin(int id) {
        jdbcTemplate.update(DELETE_CLINIC_ADMIN_SQL, id);
    }

    // mapToRowClinicAdmin
    private ClinicAdmin mapRowToClinicAdmin(ResultSet rs, int rowNum) throws SQLException {

        ClinicAdmin clinicAdmin = new ClinicAdmin();
        clinicAdmin.setId(rs.getInt("id"));
        clinicAdmin.setEmail(rs.getString("email"));
        clinicAdmin.setFirstName(rs.getString("f_name"));
        clinicAdmin.setLastName(rs.getString("l_name"));
        clinicAdmin.setPassword(rs.getString("password"));
        clinicAdmin.setRole(rs.getString("role"));

        return clinicAdmin;
    }
}
