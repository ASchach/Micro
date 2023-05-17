package com.example.kafka;

import com.example.Client;

public class ClientDeletionEvent {
    int id;

    public ClientDeletionEvent(int id) {
        this.id = id;
    }
}
