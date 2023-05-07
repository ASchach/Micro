package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clients")
@Slf4j
public record ClientController(ClientService clientService) {

    @CrossOrigin()
    @PostMapping()
    public void registerClient(@RequestBody ClientRegistrationRequest clientRegistrationRequest) {
        log.info("new client registration {}", clientRegistrationRequest);
        clientService.registerClient(clientRegistrationRequest);

    }
}
