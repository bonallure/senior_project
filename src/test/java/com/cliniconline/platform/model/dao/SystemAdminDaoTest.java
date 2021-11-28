package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.Admin;
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
public class SystemAdminDaoTest {

    @Qualifier("systemAdminDaoImpl")
    @Autowired
    protected AdminDao dao;

    @Before
    public void setUp() throws Exception {
        List<Admin> systemAdminList = dao.getAllAdmin();

        for(Admin admin : systemAdminList){
            dao.deleteAdmin(admin.getId());
        }
    }

    @Test
    public void addGetDeleteAdmin() {
        // Arrange
        SystemAdmin systemAdmin = new SystemAdmin();
        systemAdmin.setEmail("laurent@cliniconline.com");
        systemAdmin.setFirstName("Laurent");
        systemAdmin.setLastName("Mwamba");
        systemAdmin.setPassword("password".hashCode());

        // Act
        systemAdmin = (SystemAdmin) dao.addAdmin(systemAdmin);

        SystemAdmin systemAdmin1 = (SystemAdmin) dao.getAdmin(systemAdmin.getId());

        // Assert the added admin is equal to the admin received
        assertEquals(systemAdmin, systemAdmin1);

        // Act (delete)
        //dao.deleteAdmin(systemAdmin.getId());
        //systemAdmin1 = (SystemAdmin) dao.getAdmin(systemAdmin.getId());

        // Assert the admin is null
        //assertNull(systemAdmin1);
    }


    @Test
    public void updateAdmin() {
        // Arrange
        SystemAdmin systemAdmin = new SystemAdmin();
        systemAdmin.setEmail("laurent@cliniconline.com");
        systemAdmin.setFirstName("Laurent");
        systemAdmin.setLastName("Mwamba");
        systemAdmin.setPassword("password".hashCode());

        // Act
        systemAdmin = (SystemAdmin) dao.addAdmin(systemAdmin);
        SystemAdmin systemAdmin1 = (SystemAdmin) dao.getAdmin(systemAdmin.getId());

        // Assert the added admin is equal to the admin received
        assertEquals(systemAdmin, systemAdmin1);

        // Arrange
        systemAdmin.setEmail("bona@cliniconline.com");

        // Act
        dao.updateAdmin(systemAdmin);

        systemAdmin = (SystemAdmin) dao.getAdmin(systemAdmin.getId());

        // Assert the two admins are not equal to one another
        assertNotEquals(systemAdmin, systemAdmin1);
        assertNotNull(systemAdmin);
    }

}