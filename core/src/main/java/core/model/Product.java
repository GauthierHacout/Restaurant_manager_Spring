package core.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private double price;

    private ProductType productType;

    @ManyToOne
    private Order order;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Order getOrder() {
        return order;
    }
}
