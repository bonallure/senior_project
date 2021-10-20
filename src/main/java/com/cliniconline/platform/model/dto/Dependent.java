package com.cliniconline.platform.model.dto;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by bonallure on 10/9/21
 */
@Entity
public class Dependent extends Patient implements User{

    @ManyToOne
    protected AdultPatient guardian;

    public Dependent() {
    }

    public Dependent(String email, String firstName, String lastName, String password, String address, int phoneNumber,
                     Date DOB, int SSN, Role role) {
        super(email, firstName, lastName, password, address, phoneNumber, DOB, SSN, role);
    }

    public AdultPatient getGuardian() {

        return guardian;
    }

    public void setGuardian(AdultPatient guardian) {

        this.guardian = guardian;
    }
}
