package com.cliniconline.platform.model.dto;

import com.cliniconline.platform.model.dao.AdultPatientDao;
import com.cliniconline.platform.model.dao.DependentDao;
import com.cliniconline.platform.model.dao.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bonallure on 10/8/21
 */

public class ClinicAdmin implements Admin{

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private final Role role = Role.CLINIC_ADMIN;

    @Autowired
    protected AdultPatientDao adultPatientDao;

    @Autowired
    protected DoctorDao doctorDao;

    @Autowired
    protected DependentDao dependentDao;

    public ClinicAdmin() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.toString();
    }

    @Override
    public AdultPatient addPatient(AdultPatient adultPatient) {
        return adultPatientDao.addAdultPatient(adultPatient);
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorDao.addDoctor(doctor);
    }

    @Override
    public AdultPatient graduatePatient(String email, Dependent dependent) {
        AdultPatient newPatient = new AdultPatient();
        newPatient.setEmail(email);
        // TODO
        return adultPatientDao.addAdultPatient(newPatient);
    }

    public int getId() {
        return id;
    }

    @Override
    public Dependent addDependent(Dependent dependent) {
        return dependentDao.addDependent(dependent);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void changePassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Clinic Admin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", authority=" + role +
                '}';
    }

    @Override
    public void setRole(String role) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClinicAdmin that = (ClinicAdmin) o;

        if (id != that.id) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!email.equals(that.email)) return false;
        if (!password.equals(that.password)) return false;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
