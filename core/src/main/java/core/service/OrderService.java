package core.service;

import core.model.Order;
import core.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService extends GenericService<Order> {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setInactiveAndSave(Order order) {
        order.setActive(false);
        order.getTable().setOccupied(false);
        orderRepository.save(order);
    }

    public Order findActiveOrderWithItemsByTableId(Long id) {
        return orderRepository.findActiveOrderWithItemsByTableId(id);
    }
}