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
        Optional<Venue> venueFromDb1 = venueRepository.findById(1);
        if (venueFromDb1.isPresent()) {
            model.addAttribute("venue1", venueFromDb1.get());
        }
        Optional<Venue> venueFromDb2 = venueRepository.findById(2);
        if (venueFromDb2.isPresent()) {
            model.addAttribute("venue2", venueFromDb2.get());
        }
        Optional<Venue> venueFromDb3 = venueRepository.findById(3);
        if (venueFromDb3.isPresent()) {
            model.addAttribute("venue3", venueFromDb3.get());
        }
        return "zaaldetails";
    }
}
