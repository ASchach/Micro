package com.example.presentation;

import java.math.BigInteger;

public record ClientUpdateRequest(
        BigInteger cpr,
        String firstName,
        String lastName,
        String address,
        Integer birthYear) {
}
