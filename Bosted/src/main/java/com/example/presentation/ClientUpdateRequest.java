package com.example.presentation;

import java.math.BigInteger;

public record ClientUpdateRequest(
        Integer id,
        BigInteger cpr,
        String firstName,
        String lastName,
        String address,
        Integer birthYear) {
}
