package com.cliniconline.platform.model.dto;

/**
 * Created by bonallure on 10/8/21
 */
public interface Admin {

    void setId(int id);

    int getId();

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    int getPassword();

    void setPassword(int password);

    void changePassword(int password);

    @Override
    String toString();

    void setRole(String role);

    String getRole();
}
