package com.example.domain;

import com.example.domain.event.EventPublisher;
import com.example.persistence.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

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
        try{
            return clientRepository.findAll();
        }
        catch (Exception e) {
            System.err.println(
                    "Error trying to retrieve client list: " + e.getMessage()
            );
        }
        return null;
    }

    //Delete a client from ID
    public void deleteClient(int id)  {
        try {
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
                "Error trying to delete Client with ID: " + id + " " +  ex.getMessage());
        }
    }

    //Update client from ID
    public void updateClient(Client client) {
        try {
            for (int i = 0; i < clientRepository.findAll().size()+1 ; i++) {
                if (Objects.equals(clientRepository.findAll().get(i).getCpr(),
                        client.getCpr())){
                    clientRepository.save(client);
                }
            }
        }
        catch (Exception ex) {
            System.err.println(
                    "Error trying to update client with ID: " + client.getId() + ex.getMessage());
        }
    }

}