package com.example;


import com.example.kafka.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
        log.info("new client registration {}", clientRegistrationRequest);
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
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        log.info("delete client with id {}", id);
        eventPublisher.publishClientDeletionEvent(id);
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
