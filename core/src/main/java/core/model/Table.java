package core.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@javax.persistence.Table(
        name = "restaurant_table",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"" +
                    "number",
                    "restaurant_id"
            })
        }
)
public class Table extends GenericEntity{

    private int number;

    private boolean occupied;

    @Column(name = "number_of_seats")
    @Min(value=1, message = "Capacity should be greater than 0")
    private int numberOfSeats;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Table() {
    }

    public void addEmptyActiveOrder() {
        this.orders.add(new Order(true, this));
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean hasActiveOrder() {
        for (Order order : this.orders) {
            if (order.isActive()) {
                return true;
            }
        }
        return false;
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
