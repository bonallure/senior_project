package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.AdminDao;
import com.cliniconline.platform.model.dto.Admin;
import com.cliniconline.platform.model.dto.ClinicAdmin;
import com.cliniconline.platform.model.dto.SystemAdmin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bonallure on 10/21/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClinicAdminDaoImplTest {

    @Qualifier("clinicAdminDaoImpl")
    @Autowired
    protected AdminDao dao;

    @Before
    public void setUp() throws Exception {
        List<Admin> clinicAdminList = dao.getAllAdmin();

        for(Admin admin : clinicAdminList){
            dao.deleteAdmin(admin.getId());
        }
    }

    @Test
    public void addGetDeleteAdmin() {
        // Arrange
        ClinicAdmin clinicAdmin = new ClinicAdmin();
        clinicAdmin.setEmail("laurent@cliniconline.com");
        clinicAdmin.setFirstName("Laurent");
        clinicAdmin.setLastName("Mwamba");
        clinicAdmin.setPassword("password");

        // Act
        clinicAdmin = (ClinicAdmin) dao.addAdmin(clinicAdmin);

        ClinicAdmin clinicAdmin1 = (ClinicAdmin) dao.getAdmin(clinicAdmin.getId());

        // Assert the added admin is equal to the admin received
        assertEquals(clinicAdmin, clinicAdmin1);

        // Act (delete)
        dao.deleteAdmin(clinicAdmin.getId());
        clinicAdmin1 = (ClinicAdmin) dao.getAdmin(clinicAdmin.getId());

        // Assert the admin is null
        assertNull(clinicAdmin1);
    }


    @Test
    public void updateAdmin() {
        // Arrange
        ClinicAdmin clinicAdmin = new ClinicAdmin();
        clinicAdmin.setEmail("laurent@cliniconline.com");
        clinicAdmin.setFirstName("Laurent");
        clinicAdmin.setLastName("Mwamba");
        clinicAdmin.setPassword("password");

        // Act
        clinicAdmin = (ClinicAdmin) dao.addAdmin(clinicAdmin);
        ClinicAdmin clinicAdmin1 = (ClinicAdmin) dao.getAdmin(clinicAdmin.getId());

        // Assert the added admin is equal to the admin received
        assertEquals(clinicAdmin, clinicAdmin1);

        // Arrange
        clinicAdmin.setEmail("bona@cliniconline.com");

        // Act
        dao.updateAdmin(clinicAdmin);

        clinicAdmin = (ClinicAdmin) dao.getAdmin(clinicAdmin.getId());

        // Assert the two admins are not equal to one another
        assertNotEquals(clinicAdmin, clinicAdmin1);
        assertNotNull(clinicAdmin);
    }
}