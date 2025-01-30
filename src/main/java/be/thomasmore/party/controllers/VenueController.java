package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import be.thomasmore.party.model.Venue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VenueController {
    @GetMapping("/venuedetails")
    public String venue(Model model) {
        Venue venue = new Venue();
        venue.setVenueName("sportpaleis");
        venue.setLinkMoreInfo("https://www.sportpaleis.be/en/calendar?venue=AS");
        model.addAttribute("venue", venue);
        model.addAttribute("venueName", venue.getVenueName());
        model.addAttribute("linkMoreInfo", venue.getLinkMoreInfo());
        return "venuedetails";
    }
}
