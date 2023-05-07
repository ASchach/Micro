package com.example;

public record ClientRegistrationRequest(
         Integer cpr,
         String firstName,
         String lastName,
         String address,
         Integer birthYear) {
}
