package com.example.domain;
import com.example.persistence.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ClientServiceTest {
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        clientService = new ClientService(clientRepository);
    }

    @Test
    void registerClient() {
        //Set up mock data for testing
        BigInteger cpr = new BigInteger("0987654321");
        Client client = new Client(
                cpr, "John", "Doe",
                "131 boulevard", 1900);
        List<Client> existingClients = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(existingClients);

        //Attempt to register
        clientService.registerClient(client);

        //Verification that the save method was called to register new client
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void deleteClient() {
        //Set up mock data for testing
        BigInteger cprToDelete = new BigInteger("1234567890");
        BigInteger cpr1 = new BigInteger("0987654321");
        BigInteger cpr2 = new BigInteger("1234567890");
        Client client1 = new Client(
                cpr1, "John", "Doe",
                "131 boulevard", 1900);
        Client client2 = new Client(
               cpr2 , "Jane", "Smith",
                "255 road", 1950);
        List<Client> existingClients = new ArrayList<>();
        existingClients.add(client1);
        existingClients.add(client2);
        when(clientRepository.findAll()).thenReturn(existingClients);

        //Attempt to delete
        clientService.deleteClient(cprToDelete);

        //Verification that delete was called
        verify(clientRepository, times(1)).delete(client2);
    }

    @Test
    void updateClient() {
        //Set up mock data for testing
        BigInteger cpr = new BigInteger("1234567890");
        List<Client> existingClients = new ArrayList<>();
        Client client = new Client(
                cpr, "John", "Doe",
                "131 boulevard", 1900);
        existingClients.add(client);
        Client updatedClient = new Client(
                cpr, "Jane", "Smith", "255 road", 1950
        );
        when(clientRepository.findAll()).thenReturn(existingClients);

        //Attempt the update function
        clientService.updateClient(updatedClient);

        //Verification that the save method was called to update the client
        verify(clientRepository, times(1)).save(updatedClient);
    }

    @Test
    void updateClient_clientDoesNotExist() {
        //Set up mock data for testing
        BigInteger cpr1 = new BigInteger("1234567890");
        BigInteger cpr2 = new BigInteger("0987654321");
        List<Client> existingClients = new ArrayList<>();
        Client client = new Client(
                cpr1, "John", "Doe",
                "131 boulevard", 1900);
        existingClients.add(client);
        Client updatedClient = new Client(
                cpr2, "Jane", "Smith", "255 road", 1950
        );
        when(clientRepository.findAll()).thenReturn(existingClients);

        //Attempt the update function
        clientService.updateClient(updatedClient);

        //Verification that the save method was not called
        verify(clientRepository, never()).save(updatedClient);
    }
}