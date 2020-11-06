package core.repository;

import core.model.Order;
import core.model.Table;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TableRepository extends GenericRepository<Table> {

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.table.id=:id AND o.active=true")
    Order findActiveOrderWithItemsById(@Param("id") Long id);

    @Query("SELECT DISTINCT t FROM Table t LEFT JOIN FETCH t.orders WHERE t.id=:id")
    Table findByIdWithOrders(@Param("id") Long id);
}
