package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.AnimalRepository;
import be.thomasmore.party.repositories.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PartyController {
    private Logger logger = LoggerFactory.getLogger(PartyController.class);
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/Party")
    public String party(Model model) {
        Iterable<Party> parties = partyRepository.findAll();
        model.addAttribute("allparties", parties);
        return "Party";
    }

    @GetMapping("/animalDetails/{id}")
    public String animalDetails(Model model, @PathVariable int id) {
        int previd = id;
        int nextid = id;
        if (id == 1) {
            nextid = id + 1;
            previd = (int) animalRepository.count();
        }
        if (id == animalRepository.count()) {
            nextid = 1;
            previd = id - 1;
        }
        if (id > 1 && id < animalRepository.count()) {
            previd = id - 1;
            nextid = id + 1;
        }
        Optional<Animal> animal = animalRepository.findById(id);
        if (animal.isPresent()) {
            model.addAttribute("animal", animal.get());
            model.addAttribute("previd", previd);
            model.addAttribute("nextid", nextid);
        }
        return "animalDetails";
    }

    @GetMapping("/partydetails/{id}")
    public String partyDetails(Model model, @PathVariable int id) {
        int previd = id;
        int nextid = id;
        if (id == 1) {
            nextid = id + 1;
            previd = (int) partyRepository.count();
        }
        if (id == partyRepository.count()) {
            nextid = 1;
            previd = id - 1;
        }
        if (id > 1 && id < partyRepository.count()) {
            previd = id - 1;
            nextid = id + 1;
        }
        Optional<Party> party = partyRepository.findById(id);
        if (party.isPresent()) {
            model.addAttribute("party", party.get());
            model.addAttribute("previd", previd);
            model.addAttribute("nextid", nextid);
        }
        return "partydetails";
    }
}
