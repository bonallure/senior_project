package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.AdultPatient;
import com.cliniconline.platform.model.dto.Dependent;
import com.cliniconline.platform.model.dto.Doctor;

import javax.print.Doc;
import java.util.List;

/**
 * Created by bonallure on 10/19/21
 */
public interface DependentDAO {

    Dependent getDependent(Long id);

    List<Dependent> getAllDependentsPerAdultPatient(AdultPatient adultPatient);

    List<Dependent> getAllDependents();

    Dependent addDependent(Dependent dependent);

    void updateDependent(Dependent dependent);

    void deleteDependent(Long id);

    List<Dependent> getAllDependentByDoctor(Doctor doctor);
}
