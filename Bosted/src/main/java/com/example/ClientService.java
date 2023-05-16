package com.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;




@Service
public class ClientService {

    public ClientService(ClientRepository clientRepository, KafkaTemplate<Integer, Client> kafkaTemplate) {
        this.clientRepository = clientRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    ClientRepository clientRepository;
    KafkaTemplate<Integer, Client> kafkaTemplate;


    //Create client
    public void registerClient(ClientRegistrationRequest clientRegistrationRequest) {
        try {
            Client client = Client.builder()
                    .cpr(clientRegistrationRequest.cpr())
                    .firstName(clientRegistrationRequest.firstName())
                    .lastName(clientRegistrationRequest.lastName())
                    .address(clientRegistrationRequest.address())
                    .birthYear(clientRegistrationRequest.birthYear())
                    .build();
            clientRepository.save(client);
            kafkaTemplate.send("postClient", client);

        }
        catch (Exception ex) {
            System.err.println("Error trying to register client: " + ex.getMessage());
        }
        //todo: poll database for Client by ID, if sucessfull: publish to kafka
    }


    //Get all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    //Delete a client from ID
    public void deleteClient(int id)  {
        try {
            if (clientRepository.findAll().contains(clientRepository.getReferenceById(id))){
                clientRepository.deleteById(id);
            }
            else {
                System.err.println("Client not found with ID: " + id);
            }
        }
        catch (Exception ex) {
            System.err.print(
                    "Error trying to delete Client with ID: " + id + ex.getMessage());
        }
    }


    //Update client from ID
    public void updateClient(ClientUpdateRequest clientUpdateRequest, int id) {
        try {
            Optional<Client> optionalClient = clientRepository.findById(id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                client.setCpr(clientUpdateRequest.getCpr());
                client.setFirstName(clientUpdateRequest.getFirstName());
                client.setLastName(clientUpdateRequest.getLastName());
                client.setAddress(clientUpdateRequest.getAddress());
                client.setBirthYear(clientUpdateRequest.getBirthYear());
                clientRepository.save(client);
            }
            else {
                System.err.println(
                        "Client not found with id " + id);
            }
        }
        catch (Exception ex) {
            System.err.println(
                    "Error trying to update client with ID: " + id + ex.getMessage());
        }
    }

}
