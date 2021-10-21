package com.cliniconline.platform.model.dto;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by bonallure on 10/9/21
 */

public class Dependent extends Patient implements User{

    protected AdultPatient guardian;

    public Dependent() {
    }

    public AdultPatient getGuardian() {

        return guardian;
    }

    public void setGuardian(AdultPatient guardian) {

        this.guardian = guardian;
    }
}
