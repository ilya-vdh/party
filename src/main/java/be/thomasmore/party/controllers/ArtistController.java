package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/artistlist")
    public String artistContoller(Model model) {
        final Iterable<Artist> allartists = artistRepository.findAll();
        model.addAttribute("artists", allartists);
        return "artistlist";
    }

    @GetMapping("/artistdetails/{id}")
    public String artistDetails(Model model, @PathVariable int id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            model.addAttribute("artist", artist.get());
        }
        return "artistdetails";
    }

}
