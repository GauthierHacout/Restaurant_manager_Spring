package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import core.model.Restaurant;
import core.service.RestaurantService;

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
        return "restaurantsLogin";
    }

    @PostMapping()
    public String checkLogin(@ModelAttribute("restaurant") Restaurant restaurant) {
        System.out.println("RESTAURANT : "+ restaurant);
        System.out.println(restaurant.getName());
        Restaurant test = restaurantService.findByName(restaurant.getName());
        System.out.println("Restaurant  : "+test);
        if (test == null) {
            return "redirect:/restaurant?error=true";
        }
        return "redirect:/restaurant";
    }
}
