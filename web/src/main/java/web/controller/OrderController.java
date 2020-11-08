package web.controller;

import core.model.Order;
import core.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    final private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "order/{id}")
    public String makeOrderInactive(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Order order = orderService.findById(id).orElse(null);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find this order");
        }

        logger.info("Order with id : {} was set to inactive", order.getId());
        orderService.setInactiveAndSave(order);

        redirectAttributes.addAttribute("id", order.getTable().getRestaurant().getId());
        return "redirect:/restaurant/{id}/tables";
    }
}
