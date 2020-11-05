package web.controller;

import core.model.Table;
import core.service.TableService;
import core.service.implementation.StartTableService;
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

    private final StartTableService startTableService;

    public TableController(TableService tableService, StartTableService startTableService) {
        this.tableService = tableService;
        this.startTableService = startTableService;
    }

    @GetMapping("table/{id}/start")
    public String startTableService(@PathVariable("id") long id,  RedirectAttributes redirectAttributes) {
        Table table = tableService.findById(id).orElse(null);
        if (table==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find table");
        }

        startTableService.call(table);
        redirectAttributes.addAttribute("id", table.getRestaurant().getId());
        return "redirect:/restaurant/{id}/tables";
    }

    @GetMapping("table/{id}")
    public String showTable(@PathVariable("id") long id, ModelMap model) {
        Table table = tableService.findById(id).orElse(null);
        if (table==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find table");
        }

        model.put("table", table);
        return "tableShow";
    }
}
