package web.controller;

import core.model.*;
import core.service.OrderService;
import core.service.ProductService;
import core.service.RestaurantService;
import core.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TableController {

    private final TableService tableService;
    private final OrderService orderService;
    private final ProductService productService;
    private final RestaurantService restaurantService;

    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    public TableController(TableService tableService, OrderService orderService, RestaurantService restaurantService, ProductService productService) {
        this.orderService = orderService;
        this.tableService = tableService;
        this.productService = productService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("table/{id}/start")
    public String startTableService(@PathVariable("id") Long id,  RedirectAttributes redirectAttributes) {
        Table table = tableService.findByIdWithOrders(id);
        if (table==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find table");

        try {
            tableService.saveWithNewActiveOrder(table);
            logger.info("Table with id : {} has new customers", table.getId());
        } catch(Error e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        redirectAttributes.addAttribute("id", table.getRestaurant().getId());
        return "redirect:/restaurant/{id}/tables";
    }

    @GetMapping("table/{id}")
    public String showTable(@PathVariable("id") Long id, ModelMap model) {
        Table table = tableService.findById(id).orElse(null);
        if (table==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find table");
        }

        Order order = orderService.findActiveOrderWithItemsByTableId(id);
        if (order == null) {
            logger.info("Table with id : {} has no active order", table.getId());
            model.put("error", "There is no active order for this table currently");
        }

        Map<Long, String> products = productService
                .findAllByRestaurantId(table.getRestaurant().getId())
                .stream().collect(Collectors.toMap(Product::getId, Product::toString));

        model.put("table", table);
        model.put("order", order);
        model.put("products", products);
        model.put("orderItem", new OrderItem());
        return "tableOrderEdition";
    }

    @PostMapping("/restaurant/{id}/table")
    public String createTable(@PathVariable("id") Long restaurantId,
                              @ModelAttribute("table") @Valid Table createdTable,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn(bindingResult.toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create table with theses properties");
        }


        Restaurant restaurant = restaurantService.findById(restaurantId).orElse(null);
        if (restaurant==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find Restaurant");
        }

        try {
            tableService.setRestaurantAndSave(restaurant, createdTable);
            logger.info("Restaurant with id : {} has a new table", restaurantId);
        } catch(Exception e) {
            logger.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossible to create a table with theses properties");
        }

        return "redirect:/restaurant/{id}/tables";
    }
}
