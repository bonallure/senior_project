package com.cliniconline.platform.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by bonallure on 10/9/21
 */
public class Dependent extends Patient implements User{

    @ManyToOne
    private Patient gardian;
    protected int age;

    public Dependent() {
    }

    public Dependent(String email, String firstName, String lastName, Date DOB, int SSN) {
        super(email, firstName, lastName, DOB, SSN);
    }

    Patient getGardian(){

        return null;
    }

    boolean isLegal() {

        return this.age >= 18;
    }
}
