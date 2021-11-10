package com.cliniconline.platform.dao;

import com.cliniconline.platform.model.dto.Admin;
import java.util.List;

/**
 * Created by bonallure on 10/14/21
 */

public interface AdminDao {

    Admin getAdmin(int id);

    List<Admin> getAllAdmin();

    Admin addAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(int id);
}
