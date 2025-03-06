package be.thomasmore.party.controllers.admin;

import be.thomasmore.party.controllers.ArtistController;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class PartyAdminController {
    private Logger logger = LoggerFactory.getLogger(PartyAdminController.class);
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private VenueRepository venueRepository;


    @GetMapping({"/partyedit/{id}"})
    public String partyEdit(Model model, @PathVariable int id) {
        logger.info(String.format("partyEdit -- id=%d", id));
        model.addAttribute("venues", venueRepository.findAll());
        return "admin/partyedit";
    }

    @ModelAttribute("party")
    public Party findParty(@PathVariable(required = false) Integer id) {
        logger.info("findParty " + id);
        if (id == null) {
            return new Party();
        }
        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent()) {
            return optionalParty.get();
        }
        return null;
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(@PathVariable Integer id, Party party) {
        logger.info("partyEditPost " + id + " -- new name=" + party.getName());
        partyRepository.save(party);
        return "redirect:/partydetails/" + id;
    }

    @GetMapping("/partynew")
    public String partyNew(Model model) {
        model.addAttribute("venue", venueRepository.findAll());
        return "/admin/partynew";
    }

    @PostMapping("/partynew")
    public String partyCreatePost( Party party) {
        logger.info(" -- new name=" + party.getName());
        partyRepository.save(party);
        return "redirect:/partydetails/" + party.getId();
    }

}

