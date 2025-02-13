package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;

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

    @GetMapping("/pay")
    public String pay(Model model) {
        String weekendzin = weekendzin();
        model.addAttribute("weekendzin", weekendzin);
        return "pay";
    }

    public boolean ishetWeekendofniet(){
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY){
            return true;
        }
        return false;
    }

    public String weekendzin(){
        boolean weekendofniet = ishetWeekendofniet();
        if (weekendofniet){
            return "Prettig weekend, je hebt het verdient!";
        }
        return "Voor je het weet is het weekend!";
    }
}
