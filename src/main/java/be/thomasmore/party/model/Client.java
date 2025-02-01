package be.thomasmore.party.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class Client {

    @Id
    private Integer id;
    private String name;
    private Integer nrOfOrders;
    private Double totalAmount;
    private Double discountTaken;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNrOfOrders() {
        return nrOfOrders;
    }

    public void setNrOfOrders(Integer nrOfOrders) {
        this.nrOfOrders = nrOfOrders;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscountTaken() {
        return discountTaken;
    }

    public void setDiscountTaken(Double discountTaken) {
        this.discountTaken = discountTaken;
    }
}
