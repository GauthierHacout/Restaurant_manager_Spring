package core.model;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name ="restaurant_product")
public class Product extends GenericEntity{

    private String name;

    private double price;

    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @ManyToOne
    private Restaurant restaurant;

    public Product() {
    }

    public Product(Long id) {
        this.setId(id);
    }

    public Restaurant getRestaurant() {
        return restaurant;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
