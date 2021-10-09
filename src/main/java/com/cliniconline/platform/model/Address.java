package com.cliniconline.platform.model;

/**
 * Created by bonallure on 10/8/21
 */
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int zipCode;
    private String country = "USA";

    public Address(String addressLine1, String addressLine2, String city, String state, int zipCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (zipCode != address.zipCode) return false;
        if (!addressLine1.equals(address.addressLine1)) return false;
        if (addressLine2 != null ? !addressLine2.equals(address.addressLine2) : address.addressLine2 != null)
            return false;
        if (!city.equals(address.city)) return false;
        if (!state.equals(address.state)) return false;
        return country.equals(address.country);
    }

    @Override
    public int hashCode() {
        int result = addressLine1.hashCode();
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0);
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zipCode;
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return addressLine1 + '\n' +
                addressLine2 + '\n' +
                city + ", " +
                state + ", " + zipCode + '\n' +
                country;
    }
}
