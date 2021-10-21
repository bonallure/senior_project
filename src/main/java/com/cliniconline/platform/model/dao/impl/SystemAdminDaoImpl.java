package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdminDao;
import com.cliniconline.platform.model.dto.Admin;
import com.cliniconline.platform.model.dto.SystemAdmin;
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
public class SystemAdminDaoImpl implements AdminDao {

    // Prepare statements
    private static final String INSERT_SYSTEM_ADMIN_SQL =
            "insert into system_admin (email, f_name, l_name, password, role) values (?, ?, ?, ?, ?)";

    private static final String SELECT_SYSTEM_ADMIN_SQL =
            "select * from system_admin where id = ?";

    private static final String SELECT_ALL_SYSTEM_ADMINS_SQL =
            "select * from system_admin";

    private static final String DELETE_SYSTEM_ADMIN_SQL =
            "delete from system_admin where id = ?";

    private static final String UPDATE_SYSTEM_ADMIN_SQL =
            "update system_admin set email = ?, f_name = ?, l_name = ?, password = ?, role = ? where id = ?";


    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SystemAdminDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Admin getAdmin(int id) {

        try {
            return jdbcTemplate.queryForObject(SELECT_SYSTEM_ADMIN_SQL, this::mapRowToSystemAdmin, id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Admin> getAllAdmin() {

        return jdbcTemplate.query(SELECT_ALL_SYSTEM_ADMINS_SQL, this::mapRowToSystemAdmin);
    }

    @Override
    public Admin addAdmin(Admin admin) {

        jdbcTemplate.update(INSERT_SYSTEM_ADMIN_SQL,
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

        jdbcTemplate.update(UPDATE_SYSTEM_ADMIN_SQL,
                admin.getEmail(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getPassword(),
                admin.getRole(),
                admin.getId());
    }

    @Override
    public void deleteAdmin(int id) {

        jdbcTemplate.update(DELETE_SYSTEM_ADMIN_SQL, id);
    }

    // mapToRowSystemAdmin
    private SystemAdmin mapRowToSystemAdmin(ResultSet rs, int rowNum) throws SQLException {

        SystemAdmin systemAdmin = new SystemAdmin();
        systemAdmin.setId(rs.getInt("id"));
        systemAdmin.setEmail(rs.getString("email"));
        systemAdmin.setFirstName(rs.getString("f_name"));
        systemAdmin.setLastName(rs.getString("l_name"));
        systemAdmin.setPassword(rs.getString("password"));
        systemAdmin.setRole(rs.getString("role"));

        return systemAdmin;
    }
}
