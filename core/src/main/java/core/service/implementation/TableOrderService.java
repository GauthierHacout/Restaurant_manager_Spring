package core.service.implementation;

import core.model.Order;
import core.model.Table;
import core.service.OrderService;
import core.service.TableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TableOrderService {

    private final TableService tableService;
    private final OrderService orderService;

    public TableOrderService(TableService tableService, OrderService orderService) {
        this.tableService = tableService;
        this.orderService = orderService;
    }

    public void instanciateOrderFor(Table table) {
        createOrderFor(table);
        table.setOccupied(true);
        tableService.save(table);
    }

    private void createOrderFor(Table table) {
        Order newOrder = new Order();
        newOrder.setActive(true);
        newOrder.setTable(table);
        orderService.save(newOrder);
    }
}
