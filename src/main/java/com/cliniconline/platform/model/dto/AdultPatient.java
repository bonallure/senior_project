package com.cliniconline.platform.model.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
public class AdultPatient extends Patient implements User {

    private Set<Dependent> dependents = new HashSet<>();

    public AdultPatient() {
    }

    public Set<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(Set<Dependent> dependents){
        dependents.addAll(dependents);
    }
}
