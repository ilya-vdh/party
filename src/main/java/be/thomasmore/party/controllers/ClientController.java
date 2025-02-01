package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Client;
import be.thomasmore.party.repositories.ClientRepository;
import be.thomasmore.party.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;


    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client")
    public String clientGreeting(Model model) {
        Optional<Client> clientFromDb = clientRepository.findById(1);
        clientFromDb.ifPresent(client -> model.addAttribute("client", client));
        clientFromDb.ifPresent(client -> model.addAttribute("clientmessage", clientService.goedklantofniet(client)));
        return "client";
    }

    public void calculateDiscount() {

    }


}
