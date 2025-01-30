package be.thomasmore.party.controllers;

import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import be.thomasmore.party.model.Venue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;
    @GetMapping("/venuedetails")
    public String venue(Model model) {
        Optional<Venue> venueFromDb = venueRepository.findById(1);
        venueFromDb.ifPresent(venue -> model.addAttribute("venue", venue));
        return "venuedetails";
    }
}
