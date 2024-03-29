package core.service;

import core.model.Order;
import core.model.OrderItem;
import core.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class OrderItemService extends GenericService<OrderItem>{

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void setOrderAndSave(Order order, OrderItem orderItem) {
        orderItem.setOrder(order);
        orderItem.setTotalPrice(orderItem.getAmount()*orderItem.getProduct().getPrice());
        orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findByOrderId(Long id) {
        return orderItemRepository.findByOrderId(id);
    }
}
