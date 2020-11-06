package core.model;

import javax.persistence.*;
import java.util.List;

@Entity
@javax.persistence.Table(name= "table_order")
public class Order extends GenericEntity{

    private boolean active;

    @ManyToOne
    private Table table;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}