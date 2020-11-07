package web.controller;

import core.model.OrderItem;
import core.service.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderItemController {

    final private OrderItemService orderItemService;

    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @RequestMapping(value = "orderitem/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrderItem(@PathVariable("id") long id) {

        logger.info("Order Item with id : {} was delete", id);
        orderItemService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(value = "order/{id}/orderitem")
    public String createOrderItem(@ModelAttribute("orderItem") OrderItem orderItem, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        //Check if order with this id exist

        //Check if the product with the given name exist, fetch it (?)

        //Set fetched product to OrderItem.product

        //Set OrderId to the OrderItem.Order.Id

        //SAVE ORDER ITEM ?

        logger.info("New Order Item was created for Order with id : {}", id);
        OrderItem createdOrderItem = orderItemService.save(orderItem);

        redirectAttributes.addAttribute("id", createdOrderItem.getOrder().getTable().getId());
        return "redirect:/table/{id}";
    }

}
