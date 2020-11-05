package core.model;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name= "table_order")
public class Order extends GenericEntity{

    private boolean active;

    @ManyToOne
    private Table table;

    public Order() {
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isActive() {
        return active;
    }

    public Table getTable() {
        return table;
    }
}