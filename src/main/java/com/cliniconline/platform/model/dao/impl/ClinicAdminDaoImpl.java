package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdminDao;
import com.cliniconline.platform.model.dto.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bonallure on 10/21/21
 */
@Repository
public class ClinicAdminDaoImpl implements AdminDao {

    // Prepare statements
    private static final String INSERT_CLINIC_ADMIN_SQL =
            "insert in clinic_admin (id, email, f_name, l_name, password, role) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_CLINIC_ADMIN_SQL =
            "select * from clinic_admin where id = ?";

    private static final String SELECT_ALL_CLINIC_ADMINS_SQL =
            "select * from clinic_admin";

    private static final String DELETE_CLINIC_ADMIN_SQL =
            "delete from clinic_admin where id = ?";

    private static final String UPDATE_CLINIC_ADMIN_SQL =
            "update clinic_admin set id = ?, email = ?, f_name = ?, l_name = ?, password = ?, role = ?, where id = ?";

    @Override
    public Admin getAdmin(int id) {
        return null;
    }

    @Override
    public List<Admin> getAdmin() {
        return null;
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return null;
    }

    @Override
    public void updateAdmin(Admin admin) {

    }

    @Override
    public void deleteAdmin(int id) {

    }
}
