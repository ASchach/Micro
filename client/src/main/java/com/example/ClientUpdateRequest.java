package com.example;

import java.math.BigInteger;

public class ClientUpdateRequest {
    BigInteger cpr;
    String firstName;
    String lastName;
    String address;
    Integer birthYear;
    Integer id;

    public BigInteger getCpr() {
        return cpr;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getId() {
        return id;
    }
}
