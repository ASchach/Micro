package com.example;

import com.example.kafka.EventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public record ClientService(ClientRepository clientRepository, EventPublisher eventPublisher) {



    //Create client
    public void registerClient(Client client) {
        try {
            clientRepository.save(client);
        }
        catch (Exception ex) {
            System.err.println("Error trying to register client: " + ex.getMessage());
        }
    }


    //Get all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    //Delete a client from ID
    public void deleteClient(int id)  {
        try {
            System.out.println("check if exists" + clientRepository.existsById(id));
            if (clientRepository.existsById(id)){
                clientRepository.deleteById(id);
                System.out.println("Client deleted with id: " + id);
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
    public void updateClient(Client client) {
        try {
            if (clientRepository.existsById(client.getId())){
                clientRepository.save(client);
            }
            else {
                System.err.println("The client does not exist");
            }
        }
        catch (Exception ex) {
            System.err.println(
                    "Error trying to update client with ID: " + client.getId() + ex.getMessage());
        }
    }

}
