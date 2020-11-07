package core.repository;

import core.model.Order;
import core.model.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends GenericRepository<OrderItem> {

    List<OrderItem> findByOrderId(Long id);
}
