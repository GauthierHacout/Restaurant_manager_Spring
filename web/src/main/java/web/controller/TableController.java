package web.controller;

import core.model.Table;
import core.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
public class TableController {

    private TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("table/{id}/start")
    public String startTableService(@PathVariable("id") long id,  RedirectAttributes redirectAttributes) {
        Optional<Table> table = tableService.find(id);

        table.get().setOccupied(true);
        tableService.save(table.get());

        redirectAttributes.addAttribute("id", table.get().getRestaurant().getId());
        return "redirect:/restaurant/{id}/tables";
    }
}
