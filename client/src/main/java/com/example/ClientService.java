package com.example;

import org.springframework.stereotype.Service;

@Service
public record ClientService(ClientRepository clientRepository) {

    public void registerClient(ClientRegistrationRequest clientRegistrationRequest) {
        Client client = Client.builder()
                        .cpr(clientRegistrationRequest.cpr())
                        .firstName(clientRegistrationRequest.firstName())
                        .lastName(clientRegistrationRequest.lastName())
                        .address(clientRegistrationRequest.address())
                        .birthYear(clientRegistrationRequest.birthYear())
                        .build();
        //todo: check if cpr valid or already exists
        clientRepository.save(client);
    }
}
