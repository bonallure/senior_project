package com.cliniconline.platform.model.dto;

/**
 * Created by bonallure on 2/9/22
 */
public class UserAuthority {
    private String username;
    private String password;
    private Role role;
    private boolean enabled;

    public UserAuthority(){
        this.enabled = true;
    }
    public UserAuthority(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthority)) return false;

        UserAuthority that = (UserAuthority) o;

        if (enabled != that.enabled) return false;
        if (!username.equals(that.username)) return false;
        if (!password.equals(that.password)) return false;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
