package core.model;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name= "table_order")
public class Order extends GenericEntity{

    private boolean payed;

    @ManyToOne
    private Table table;

    public Order() {
    }

    public boolean isPayed() {
        return payed;
    }

    public Table getTable() {
        return table;
    }
}
