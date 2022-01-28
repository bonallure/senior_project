package com.cliniconline.platform.model.dto;

import java.sql.Date;
import java.util.Set;

/**
 * Created by bonallure on 10/8/21
 */
public interface User {

    int getId();

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getAddress();

    void setAddress(String address);

    void changePassword(int password);

    void setPassword(int password);

    Long getPhoneNumber();

    void setPhoneNumber(Long number);

    Date getDOB();

    String getRole();

    String getEmail();

    int getPassword();
}
