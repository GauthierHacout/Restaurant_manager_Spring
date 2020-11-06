package web.controller;

import core.model.Table;
import core.service.TableService;
import core.service.implementation.TableOrderService;
import org.apache.commons.collections.map.StaticBucketMap;
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
    private final TableOrderService tableOrderService;

    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    public TableController(TableService tableService, TableOrderService tableOrderService) {
        this.tableService = tableService;
        this.tableOrderService = tableOrderService;
    }

    @GetMapping("table/{id}/start")
    public String startTableService(@PathVariable("id") long id,  RedirectAttributes redirectAttributes) {
        Table table = tableService.findById(id).orElse(null);
        if (table==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find table");
        }

        logger.info("Table with id : {} has new customers", table.getId());
        tableOrderService.instanciateOrderFor(table);
        redirectAttributes.addAttribute("id", table.getRestaurant().getId());
        return "redirect:/restaurant/{id}/tables";
    }

    @GetMapping("table/{id}")
    public String showTable(@PathVariable("id") long id, ModelMap model) {
        Table table = tableService.findById(id).orElse(null);
        if (table==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find table");
        }

        System.out.println("Table : "+table);

        model.put("table", table);
        return "tableShow";
    }
}
