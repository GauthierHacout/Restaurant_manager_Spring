package core.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@javax.persistence.Table(name = "restaurant_table")
public class Table extends GenericEntity{

    private int number;

    private boolean occupied;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "table")
    private List<Order> orders;

    public Table() {
    }

    public void addEmptyActiveOrder() {
        this.orders.add(new Order(true));
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Order> getOrders() { return orders; }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
