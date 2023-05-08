package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
@Slf4j
public record ClientController(ClientService clientService) {

//POST method for creating clients
    @CrossOrigin()
    @PostMapping()
    public void registerClient(@RequestBody
       ClientRegistrationRequest clientRegistrationRequest) {
        log.info("new client registration {}", clientRegistrationRequest);
        clientService.registerClient(clientRegistrationRequest);
    }

//GET method for gathering all clients
    @CrossOrigin()
    @GetMapping()
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

//DELETE method by ID for specific client
    @CrossOrigin()
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        log.info("delete client with id {}", id);
        clientService.deleteClient(id);
    }

//PUT method for updating a given client based on ID
    @CrossOrigin()
    @PutMapping("/{id}")
    public void updateClient(@PathVariable int id,
         @RequestBody ClientUpdateRequest clientUpdateRequest) {
        log.info("updating client with id {}", id);
        clientService.updateClient(clientUpdateRequest, id);
    }

}
