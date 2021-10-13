package com.cliniconline.platform.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class AdultPatient extends Patient implements User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private Set<Dependent> dependents = new HashSet<>();

    public AdultPatient() {
    }

    public AdultPatient(String email, String firstName, String lastName, String password, Address address,
                   int phoneNumber, Calendar DOB, int SSN, Doctor doctor) {
        super(email, firstName, lastName, password, address, phoneNumber, DOB, SSN, doctor);
    }

    public Set<Dependent> getDependents() {

        return dependents;
    }

    public void setDependents(Set<Dependent> dependents) {

        this.dependents = dependents;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdultPatient that = (AdultPatient) o;

        if (!id.equals(that.id)) return false;
        return dependents != null ? dependents.equals(that.dependents) : that.dependents == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (dependents != null ? dependents.hashCode() : 0);
        return result;
    }
}
