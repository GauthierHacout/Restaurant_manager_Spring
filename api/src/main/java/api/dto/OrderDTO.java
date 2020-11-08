package api.dto;

import java.util.List;

public class OrderDTO {

    private long id;

    private boolean active;

    private List<OrderItemDTO> orderItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderDTO(long id, boolean active) {
        this.id = id;
        this.active = active;
    }
}
