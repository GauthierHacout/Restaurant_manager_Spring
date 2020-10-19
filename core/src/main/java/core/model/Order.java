package core.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order extends GenericEntity{

    private boolean payed;

    @ManyToOne
    private Table table;

    @OneToMany
    private List<Product> products;

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
