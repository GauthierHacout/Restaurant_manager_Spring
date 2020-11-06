package core.service;

import core.model.Order;
import core.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService extends GenericService<Order> {

    final private OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findActiveOrderWithItemsForTable(Long id) {
        return orderRepository.findActiveOrderWithItemsForTable(id);
    }
}