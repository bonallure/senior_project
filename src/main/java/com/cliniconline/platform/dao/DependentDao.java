package com.cliniconline.platform.dao;

import com.cliniconline.platform.model.dto.Dependent;

import java.util.List;

/**
 * Created by bonallure on 10/19/21
 */

public interface DependentDao {

    Dependent addDependent(Dependent dependent);

    void updateDependent(Dependent dependent);

    void deleteDependent(int dependentId);

    Dependent getDependent(int dependentId);

    List<Dependent> getAllDependentsPerAdultPatient(int adultPatientId);

    List<Dependent> getAllDependents();

    List<Dependent> getAllDependentsPerDoctor(int doctorId);
}
