package com.cliniconline.platform.service;

import com.cliniconline.platform.model.dto.Appointment;

import java.util.Comparator;

/**
 * Created by bonallure on 11/10/21
 */
public class ServiceUtils {

    static class SortByDate implements Comparator<Appointment>
    {
        // Used for sorting in ascending order of date
        public int compare(Appointment a, Appointment b) {
            return a.getDate().compareTo(b.getDate());
        }
    }
}
