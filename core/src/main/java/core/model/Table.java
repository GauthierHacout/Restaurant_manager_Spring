package core.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Table {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private boolean occupied;

    private int number;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany
    private List<Order> commands;

    public long getId() {
        return id;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getNumber() {
        return number;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Order> getCommands() {
        return commands;
    }
}
