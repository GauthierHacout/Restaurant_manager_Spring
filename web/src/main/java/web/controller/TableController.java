package web.controller;

import core.model.Table;
import core.service.TableService;
import core.service.management.StartTableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TableController {

    private TableService tableService;

    private StartTableService startTableService;

    public TableController(TableService tableService, StartTableService startTableService) {
        this.tableService = tableService;
        this.startTableService = startTableService;
    }

    @GetMapping("table/{id}/start")
    public String startTableService(@PathVariable("id") long id,  RedirectAttributes redirectAttributes) {
        Table table = tableService.find(id).get();

        startTableService.call(table);

        redirectAttributes.addAttribute("id", table.getRestaurant().getId());
        return "redirect:/restaurant/{id}/tables";
    }
}
