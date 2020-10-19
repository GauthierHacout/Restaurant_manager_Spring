package core.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Table extends GenericEntity{

    private boolean occupied;

    private int number;

    private int numberOfSeats;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany
    private List<Order> orders;

    public boolean isOccupied() {
        return occupied;
    }

    public int getNumber() {
        return number;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
