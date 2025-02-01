package be.thomasmore.party.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Client {

    @Id
    private Integer id;
    private String name;
    private Integer nrOfOrders;
    private Double totalAmount;
    private Double discountTaken;

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
