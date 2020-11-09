package web.controller;

import core.model.Order;
import core.model.OrderItem;
import core.service.OrderItemService;
import core.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
public class OrderItemController {

    final private OrderItemService orderItemService;
    final private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    public OrderItemController(OrderItemService orderItemService, OrderService orderService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @DeleteMapping(value = "orderitem/{id}")
    public ResponseEntity deleteOrderItem(@PathVariable("id") Long id) {
        logger.info("Order Item with id : {} was delete", id);
        orderItemService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(value = "order/{id}/orderitem")
    public String createOrderItem(@ModelAttribute("orderItem") @Valid OrderItem orderItem,
                                  @PathVariable("id") Long id,
                                  RedirectAttributes redirectAttributes,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn(bindingResult.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create orderItem with theses properties");
        }

        Order order = orderService.findById(id).orElse(null);
        if (order==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find order");

        orderItemService.setOrderAndSave(order, orderItem);
        logger.info("New Order Item was created for Order with id : {}", id);

        redirectAttributes.addAttribute("id", order.getTable().getId());
        return "redirect:/table/{id}";
    }

}
