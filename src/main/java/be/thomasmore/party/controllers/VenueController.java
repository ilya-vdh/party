package be.thomasmore.party.controllers;

import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import be.thomasmore.party.model.Venue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class VenueController {
    private Logger logger = LoggerFactory.getLogger(VenueController.class);
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

    @GetMapping({"/zaaldetails","/venuelist"})
    public String venueList(Model model, @RequestParam(required=false) Boolean filterIndoor,
                            @RequestParam(required = false) Boolean filterOutdoor,@RequestParam(required = false) Integer minCapacity, @RequestParam(required = false) Integer maxCapacity
    , @RequestParam(required = false) Integer distanceToPublicTransportInKm, @RequestParam(required = false) Boolean filterFood) {
        //LET OP de naam van de request parameter heb je gekozen in het formulier
        logger.info(String.format("venuelist -- min=%d, max=%d, max=%d", minCapacity, maxCapacity, distanceToPublicTransportInKm));
        logger.info("Filter voor food: " + filterFood);
        Iterable<Venue> allVenues;
        if (filterIndoor != null || filterOutdoor != null) {
            allVenues = venueRepository.findByIndoorOutdoor(filterIndoor, filterOutdoor, minCapacity, maxCapacity, distanceToPublicTransportInKm);
        } else if (filterFood != null) {
            allVenues = venueRepository.findByFood(filterFood, minCapacity, maxCapacity, distanceToPublicTransportInKm);
        } else {
            allVenues = venueRepository.findByFilter(minCapacity, maxCapacity, distanceToPublicTransportInKm);
        }
        ArrayList<Venue> venueList = new ArrayList<>();
        allVenues.iterator().forEachRemaining(venueList::add);
        int venueCount = venueList.size();
        // Voeg de Venues toe aan het model
        model.addAttribute("venues", allVenues);
        model.addAttribute("aantalvenues", venueCount);
        return "zaaldetails";
    }
}
