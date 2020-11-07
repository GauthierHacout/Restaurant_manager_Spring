package api.controller;

import api.dto.OrderDTO;
import api.dto.OrderItemDTO;
import api.dto.TableDTO;
import core.model.Order;
import core.model.OrderItem;
import core.model.Table;
import core.service.OrderItemService;
import core.service.TableService;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Path("/table")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TableController implements RestController{

    private TableService tableService;

    private OrderItemService orderItemService;

    public TableController(TableService tableService, OrderItemService orderItemService) {
        this.tableService = tableService;
        this.orderItemService = orderItemService;
    }

    @GET
    @Path("/{tableId}")
    public TableDTO tableOrderHistory(@PathParam("tableId") long id){
        Table table = tableService.findByIdWithOrders(id);
        List<Order> orders = table.getOrders();
        TableDTO tableDTO = new TableDTO(table.getId(), table.getNumber(), table.isOccupied());
        tableDTO.setOrders(transformOrdersToOrdersDTO(orders));
        return tableDTO;
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
