package be.thomasmore.party.controllers;

import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import be.thomasmore.party.model.Venue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails/{id}", "/venuedetails"})
    public String venue(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) {
            return "venuedetails";
        }
        Optional<Venue> venueFromDb = venueRepository.findById(id);
        if (venueFromDb.isPresent()) {
            model.addAttribute("venue", venueFromDb.get());
        }
        return "venuedetails";
    }

    @GetMapping("/zaaldetails")
    public String venueList(Model model) {
        final Iterable<Venue> allvenues = venueRepository.findAll();
        model.addAttribute("venues", allvenues);
        return "zaaldetails";
    }
}
