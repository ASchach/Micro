package com.example.presentation;


import com.example.domain.Client;
import com.example.domain.ClientService;
import com.example.domain.event.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
@Slf4j
public class ClientController {

    private final EventPublisher eventPublisher;
    private final ClientService clientService;

    //Constructor
    public ClientController(EventPublisher eventPublisher, ClientService clientService) {
        this.eventPublisher = eventPublisher;
        this.clientService = clientService;
    }

    //POST method for creating clients, invokes creation of event
    @CrossOrigin()
    @PostMapping()
    public void registerClient(@RequestBody
                               ClientRegistrationRequest clientRegistrationRequest) {
        log.info("Request to register new client received: " + clientRegistrationRequest);
        eventPublisher.publishClientRegisteredEvent(clientRegistrationRequest);
    }

    //GET method for gathering all clients
    @CrossOrigin()
    @GetMapping()
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    //DELETE method by ID for specific client
    @CrossOrigin()
    @DeleteMapping("/{cpr}")
    public void deleteClient(@PathVariable BigInteger cpr) {
        log.info("Request received to delete client with CPR: " + cpr);
        eventPublisher.publishClientDeletionEvent(cpr);
    }

    //PUT method for updating a given client based on ID
    @CrossOrigin()
    @PutMapping("/{cpr}")
    public void updateClient(@PathVariable BigInteger cpr,
                             @RequestBody ClientUpdateRequest clientUpdateRequest) {
        log.info("Request received to update client with CPR: " + cpr);
        eventPublisher.publishClientUpdateEvent(clientUpdateRequest, cpr);
    }

}
