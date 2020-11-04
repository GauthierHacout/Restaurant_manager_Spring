package web.controller;

import core.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TableController {

    private TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/restaurant/{id}/tables")
    public String getTablesFromRestaurant(@PathVariable("id") long id, ModelMap model){
        model.put("tables", tableService.findByRestaurantId(id));
        return "tablesIndex";
    }
}
