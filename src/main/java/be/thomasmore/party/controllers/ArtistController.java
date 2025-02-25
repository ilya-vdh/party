package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ArtistController {
    private Logger logger = LoggerFactory.getLogger(ArtistController.class);
    @Autowired
    private ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/artistlist")
    public String artistContoller(Model model, @RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            logger.info(String.format("artistlist -- keyword=%s", keyword));
            // Voer de filter op de zoekterm uit
            final Iterable<Artist> allartists = artistRepository.findByFilter(keyword);
            model.addAttribute("allartists", allartists);
        } else {
            // Als geen zoekterm is opgegeven, haal alle artiesten op
            final Iterable<Artist> allartists = artistRepository.findAll();
            model.addAttribute("allartists", allartists);
        }
        return "artistlist";
    }

    @GetMapping("/artistdetails/{id}")
    public String artistDetails(Model model, @PathVariable int id) {
        int previd = id;
        int nextid = id;
        if (id == 1) {
            nextid = id + 1;
            previd = (int) artistRepository.count();
        }
        if (id == artistRepository.count()) {
            nextid = 1;
            previd = id - 1;
        }
        if (id > 1 && id < artistRepository.count()) {
            previd = id - 1;
            nextid = id + 1;
        }
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            model.addAttribute("artist", artist.get());
            model.addAttribute("previd", previd);
            model.addAttribute("nextid", nextid);
        }
        return "artistdetails";
    }

}
