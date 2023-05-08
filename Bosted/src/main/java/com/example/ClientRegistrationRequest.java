package com.example;

import java.math.BigInteger;

public record ClientRegistrationRequest(
        BigInteger cpr,
        String firstName,
        String lastName,
        String address,
        Integer birthYear) {
}
