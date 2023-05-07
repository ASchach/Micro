package com.example;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

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

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }


    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
