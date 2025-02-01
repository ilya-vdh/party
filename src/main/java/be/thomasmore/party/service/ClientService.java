package be.thomasmore.party.service;

import be.thomasmore.party.model.Client;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ClientService {

    public String goedklantofniet(Client client){
        String s = groetingtovtijd();
        if (client.getNrOfOrders()< 10){
            s += " "+client.getName();
            if (client.getNrOfOrders() == 0){
                s += ", en welkom!";
            }
        }
        else {
            if (client.getNrOfOrders() < 50){
                s += " beste ";
            }
            if (client.getNrOfOrders() >= 50){
                s += " allerliefste ";
            }
            s += client.getName();
            if (client.getNrOfOrders() >= 80){
                s += ",jij bent een topper!";
            }
        }
        return s;
    }

    public String groetingtovtijd(){
        LocalTime now = LocalTime.now();
        LocalTime noon = LocalTime.NOON;
        LocalTime vijfuur = LocalTime.of(17, 0);
        LocalTime tweeentwintiguur = LocalTime.of(22, 0);
        LocalTime zesuur = LocalTime.of(6, 0);
        if (now.isBefore(noon) && now.isAfter(zesuur)) {
            return "Goedemorgen";
        }
        if (now.isAfter(noon) && now.isBefore(vijfuur)) {
            return "Goedemiddag";
        }
        if (now.isAfter(vijfuur) && now.isBefore(tweeentwintiguur)) {
            return "Goedenavond";
        }
        if (now.isAfter(tweeentwintiguur) && now.isBefore(zesuur)) {
            return "Goedennacht";
        }
        return "";
    }
}
