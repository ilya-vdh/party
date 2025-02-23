package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/","/home"})
    public String home(Model model) {
        int myCalculatedValue = 34 * 62;
        model.addAttribute("myCalculatedValue", myCalculatedValue);
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        //myName and myCity,
        //myStreet
        String myName = "Ilya Van der Hasselt";
        String myCity = "Mechelen";
        String myStreet = "Oude Antwerpsebaan 155";
        model.addAttribute("myName", myName);
        model.addAttribute("myCity", myCity);
        model.addAttribute("myStreet", myStreet);
        return "about";
    }


}
