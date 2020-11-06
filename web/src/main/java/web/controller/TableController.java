package web.controller;

import core.model.Order;
import core.model.Table;
import core.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TableController {

    private final TableService tableService;

    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("table/{id}/start")
    public String startTableService(@PathVariable("id") long id,  RedirectAttributes redirectAttributes) {
        Table table = tableService.findById(id).orElse(null);
        if (table==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find table");
        }

        logger.info("Table with id : {} has new customers", table.getId());
        table = tableService.findByIdWithOrders(id);
        tableService.saveWithNewActiveOrder(table);
        redirectAttributes.addAttribute("id", table.getRestaurant().getId());
        return "redirect:/restaurant/{id}/tables";
    }

    @GetMapping("table/{id}")
    public String showTable(@PathVariable("id") long id, ModelMap model) {
        Table table = tableService.findById(id).orElse(null);
        if (table==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find table");
        }

        Order order = tableService.findActiveOrderWithItemsById(id);
        if (order == null) {
            model.put("error", "There is no active order for this table currently");
        }

        model.put("table", table);
        model.put("order", order);
        return "tableOrderEdition";
    }
}
