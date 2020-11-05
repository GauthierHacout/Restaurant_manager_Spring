package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import core.model.Restaurant;
import core.service.RestaurantService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping()
    public String getLogin(ModelMap model, @RequestParam(value="error", defaultValue= "false", required=false) Boolean error) {
        model.addAttribute("restaurant", new Restaurant());
        if (error) {
            model.addAttribute("error", "There is no restaurant with this name");
        }
        return "restaurantLogin";
    }

    @PostMapping()
    public String checkLogin(@ModelAttribute("restaurant") Restaurant r,
                             RedirectAttributes redirectAttributes) {

        Restaurant restaurant = restaurantService.findByName(r.getName());
        if (restaurant == null) {
            return "redirect:/restaurant?error=true";
        }

        redirectAttributes.addAttribute("id", restaurant.getId());
        return "redirect:/restaurant/{id}/tables";
    }

    @GetMapping("/{id}/tables")
    public String getTables(@PathVariable("id") long id, ModelMap model){
        Optional<Restaurant> restaurant = restaurantService.find(id);
        model.put("restaurant", restaurant.get());
        return "restaurantTablesIndex";
    }
}
