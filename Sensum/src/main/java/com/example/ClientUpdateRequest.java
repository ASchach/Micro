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

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Integer getId() {
        return id;
    }
}
