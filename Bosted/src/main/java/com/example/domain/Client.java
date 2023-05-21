package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    private BigInteger cpr;
    private String firstName;
    private String lastName;
    private String address;
    private Integer birthYear;

}
