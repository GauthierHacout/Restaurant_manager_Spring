package core.repository;

import core.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends GenericRepository<Order> {

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.table.id=:id AND o.active=true")
    Order findActiveOrderWithItemsForTable(@Param("id") Long id);
}