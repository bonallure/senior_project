package com.cliniconline.platform.model.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by bonallure on 10/9/21
 */

public class Dependent extends Patient implements User{

    protected int guardianId;

    public Dependent() {
    }

    public int getGuardianId() {

        return guardianId;
    }

    public void setGuardianId(int guardianId) {

        this.guardianId = guardianId;
    }

    public boolean isAdult(){
        LocalDate localDate = getDOB().toLocalDate();
        Calendar ageOfMaturity = new GregorianCalendar(localDate.getYear() + 18, localDate.getMonthValue(),
                localDate.getDayOfMonth());

        return ageOfMaturity.before(Calendar.getInstance());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependent)) return false;
        if (!super.equals(o)) return false;

        Dependent dependent = (Dependent) o;

        return guardianId == dependent.guardianId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + guardianId;
        return result;
    }
}
