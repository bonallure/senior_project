package com.cliniconline.platform.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Created by bonallure on 10/9/21
 */
@Entity
public class Dependent extends Patient implements User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private AdultPatient guardian;


    public Dependent() {
    }

    public Dependent(String email, String firstName, String lastName, String password, Address address, int phoneNumber, Calendar DOB, int SSN, Doctor doctor) {
        super(email, firstName, lastName, password, address, phoneNumber, DOB, SSN, doctor);
    }

    public AdultPatient getGuardian() {
        return guardian;
    }

    public void setGuardian(AdultPatient guardian) {
        this.guardian = guardian;
    }

    public boolean isAdult(){
        Calendar ageOfMaturity = (Calendar) super.getDOB().clone();
        ageOfMaturity.add(Calendar.YEAR, -18);

        return ageOfMaturity.before(Calendar.getInstance());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dependent dependent = (Dependent) o;

        if (!id.equals(dependent.id)) return false;
        return guardian != null ? guardian.equals(dependent.guardian) : dependent.guardian == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (guardian != null ? guardian.hashCode() : 0);
        return result;
    }
}
