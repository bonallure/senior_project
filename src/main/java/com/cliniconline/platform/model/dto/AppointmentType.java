package com.cliniconline.platform.model.dto;

/**
 * Created by bonallure on 10/23/21
 */
public enum AppointmentType {
    VIDEO, PHONE, PHYSICAL;

    public static AppointmentType getType(String type) {
        switch (type.toUpperCase()) {
            case "VIDEO":
                return VIDEO;
            case "PHONE":
                return PHONE;
            case "PHYSICAL":
                return PHYSICAL;
            default:
                return null;
        }
    }
}
