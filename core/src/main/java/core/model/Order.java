package core.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private boolean payed;

    @ManyToOne
    private Table table;

    @OneToMany
    private List<Product> products;

    public long getId() {
        return id;
    }

    public boolean isPayed() {
        return payed;
    }

    public Table getTable() {
        return table;
    }

    public List<Product> getProducts() {
        return products;
    }
}
