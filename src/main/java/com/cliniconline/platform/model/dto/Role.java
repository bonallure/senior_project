package com.cliniconline.platform.model.dto;

/**
 * Created by bonallure on 10/8/21
 */
public enum Role {
    CLINIC_ADMIN, SYSTEM_ADMIN, DOCTOR, PATIENT, DEPENDENT;

    public static Role getRole(String role){
        switch (role){
            case "CLINIC_ADMIN":
                return CLINIC_ADMIN;
            case "SYSTEM_ADMIN":
                return SYSTEM_ADMIN;
            case "DOCTOR":
                return DOCTOR;
            case "PATIENT":
                return PATIENT;
            case "DEPENDENT":
                return DEPENDENT;
            default:
                return null;
        }
    }
}
