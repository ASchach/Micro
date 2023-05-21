package com.example.domain;

import com.example.domain.event.EventPublisher;
import com.example.persistence.ClientRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
public record ClientService(ClientRepository clientRepository, EventPublisher eventPublisher) {




    //Create client
    public void registerClient(Client client) {
        try {
            if (!clientRepository.findAll().contains(client)){
                clientRepository.save(client);
                System.out.println("Client created: " + client);
            }

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

    //Delete a client from CPR
    public void deleteClient(BigInteger cpr)  {
        try {
            for (int i = 0; i < clientRepository.findAll().size() ; i++) {
                Client clientToDelete;
                if ((Objects.equals(clientRepository.findAll().get(i).getCpr(), cpr))) {
                    clientToDelete = clientRepository.findAll().get(i);
                    clientRepository.delete(clientToDelete);
                    System.out.println("Client deleted with CPR: " + cpr);
                }
            }
        }
        catch (Exception ex) {
            System.err.print(
                "Error trying to delete Client with CPR: " + cpr + " " +  ex.getMessage());
        }
    }

    //Update client from CPR
    public void updateClient(Client client) {
        try {
            for (int i = 0; i < clientRepository.findAll().size() ; i++) {
                if (Objects.equals(clientRepository.findAll().get(i).getCpr(),
                        client.getCpr())){
                    clientRepository.save(client);
                    System.out.println("Client updated with CPR: " + client.getCpr());
                }

            }
        }
        catch (Exception ex) {
            System.err.println(
                    "Error trying to update client with CPR: " + client.getCpr() + ex.getMessage());
        }
    }

}
