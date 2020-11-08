package web.controller;

import core.model.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import core.model.Restaurant;
import core.service.RestaurantService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping()
    public String getLogin(ModelMap model, @RequestParam(value="error", defaultValue= "false", required=false) Boolean error) {
        model.addAttribute("restaurant", new Restaurant());
        if (error) model.addAttribute("error", "There is no restaurant with this name");

        return "restaurantLogin";
    }

    @PostMapping()
    public String checkLogin(@ModelAttribute("restaurant") Restaurant r, RedirectAttributes redirectAttributes) {
        Restaurant restaurant = restaurantService.findByName(r.getName());
        if (restaurant == null) return "redirect:/restaurant?error=true";

        logger.info("New connection to restaurant with name : {}", restaurant.getName());
        redirectAttributes.addAttribute("id", restaurant.getId());
        return "redirect:/restaurant/{id}/tables";
    }

    @GetMapping("/{id}/tables")
    public String getTables(@PathVariable("id") Long id, ModelMap model){
        Restaurant restaurant = restaurantService.findByIdWithTables(id);
        if (restaurant==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find Restaurant");

        model.put("restaurant", restaurant);
        model.put("table_form", new Table());
        return "restaurantTablesList";
    }
}
