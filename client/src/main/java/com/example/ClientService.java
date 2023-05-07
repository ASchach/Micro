package com.example;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

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
        clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }


    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

    public void updateClient(ClientUpdateRequest clientUpdateRequest, int id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setCpr(clientUpdateRequest.getCpr());
            client.setFirstName(clientUpdateRequest.getFirstName());
            client.setLastName(clientUpdateRequest.getLastName());
            client.setAddress(clientUpdateRequest.getAddress());
            client.setBirthYear(clientUpdateRequest.getBirthYear());
            clientRepository.save(client);
        } else {
            throw new ResourceNotFoundException("Client not found with id " + clientUpdateRequest.getId());
        }
    }

}
