package com.cliniconline.platform.model.dto;

import java.sql.Date;

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

    void changePassword(String password);

    void setPassword(String password);

    Long getPhoneNumber();

    void setPhoneNumber(Long number);

    Date getDOB();

    String getRole();

    String getEmail();

    String getPassword();

    UserAuthority getUserAuthority();
}
