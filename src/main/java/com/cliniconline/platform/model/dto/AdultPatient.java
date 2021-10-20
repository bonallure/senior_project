package com.cliniconline.platform.model.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class AdultPatient extends Patient implements User {

    @OneToMany
    @JoinColumn(name = "PATIENT_ID")
    private Set<Dependent> dependents = new HashSet<>();

    public AdultPatient() {
    }

    public AdultPatient(String email, String firstName, String lastName, String password, String address,
                        int phoneNumber, Date DOB, int SSN, Role role) {
        super(email, firstName, lastName, password, address, phoneNumber, DOB, SSN, role);
    }

    public Set<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(Set<Dependent> dependents){
        dependents.addAll(dependents);
    }
}
