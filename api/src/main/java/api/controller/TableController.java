package api.controller;

import api.dto.OrderDTO;
import api.dto.OrderItemDTO;
import api.dto.TableDTO;
import core.model.Order;
import core.model.OrderItem;
import core.model.Table;
import core.service.OrderItemService;
import core.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/table")
public class TableController {

    private TableService tableService;

    private OrderItemService orderItemService;

    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    public TableController(TableService tableService, OrderItemService orderItemService) {
        this.tableService = tableService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{tableId}")
    public ResponseEntity<Object> tableOrderHistory(@PathVariable("tableId") Long id){
        try {
            Table table = tableService.findByIdWithOrders(id);
            List<Order> orders = table.getOrders();
            TableDTO tableDTO = new TableDTO(table.getId(), table.getNumber(), table.isOccupied());
            tableDTO.setOrders(transformOrdersToOrdersDTO(orders));

            logger.info("GET - /table/{} - OK", id);
            return new ResponseEntity<>(
                    tableDTO, HttpStatus.OK
            );
        } catch (Exception e) {
            logger.error("GET - /table/{} - NOT FOUND", id);
            return new ResponseEntity<>(
                    Collections.singletonMap("error", "Could not find table with id "+ id), HttpStatus.NOT_FOUND
            );
        }
    }

    private List<OrderDTO> transformOrdersToOrdersDTO(List<Order> orders) {
        return orders.stream().map(
                order -> {
                    OrderDTO orderDto = new OrderDTO(order.getId(), order.isActive());
                    List<OrderItem> orderItems = orderItemService.findByOrderId(order.getId());
                    orderDto.setOrderItems(transformOrderItemsToOrderItemsDTO(orderItems));
                    return orderDto;
                }
        ).collect(Collectors.toList());
    }


    private List<OrderItemDTO> transformOrderItemsToOrderItemsDTO(List<OrderItem> orderItems) {
        return orderItems.stream().map(
                orderItem -> {
                    OrderItemDTO orderItemDTO = new OrderItemDTO(
                            orderItem.getId(),
                            orderItem.getTotalPrice(),
                            orderItem.getAmount(),
                            orderItem.getProduct().getName());
                    return orderItemDTO;
                }
        ).collect(Collectors.toList());
    }
}
