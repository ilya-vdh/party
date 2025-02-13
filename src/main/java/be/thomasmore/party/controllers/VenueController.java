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
        int previd = id;
        int nextid = id;
        if (id == 1) {
            nextid = id + 1;
            previd = (int) venueRepository.count();
        }
        if (id == venueRepository.count()) {
            nextid = 1;
            previd = id -1;
        }
        if (id > 1 && id < venueRepository.count()) {
            previd = id - 1;
            nextid = id + 1;
        }

        Optional<Venue> venueFromDb = venueRepository.findById(id);
        if (venueFromDb.isPresent()) {
            model.addAttribute("venue", venueFromDb.get());
            model.addAttribute("previd", previd);
            model.addAttribute("nextid", nextid);
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
