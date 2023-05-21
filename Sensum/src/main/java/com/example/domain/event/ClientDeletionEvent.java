package com.example.domain.event;

import java.math.BigInteger;

public class ClientDeletionEvent {
    BigInteger cpr;

    public ClientDeletionEvent(BigInteger cpr) {
        this.cpr = cpr;
    }
}
