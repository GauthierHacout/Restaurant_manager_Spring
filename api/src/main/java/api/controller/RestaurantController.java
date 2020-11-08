package api.controller;

import api.dto.RestaurantDTO;
import api.dto.TableDTO;
import core.model.Restaurant;
import core.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("")
    public List<RestaurantDTO> findAllRestaurants(){
        return restaurantService.findAll().stream().map(
                restaurant -> {
                    RestaurantDTO restaurantDTO = new RestaurantDTO(restaurant.getId(), restaurant.getName());
                    return restaurantDTO;
                }
        ).collect(Collectors.toList());
    }

    @GetMapping("/{restaurantId}/tables")
    public ResponseEntity<Object> findRestaurantTables(@PathVariable("restaurantId") Long id){
        try {
            Restaurant restaurant = restaurantService.findByIdWithTables(id);
            RestaurantDTO restaurantDTO = new RestaurantDTO(restaurant.getId(), restaurant.getName());
            restaurantDTO.setTables(
                    restaurant.getTables().stream().map(
                            table -> {
                                TableDTO dto = new TableDTO(table.getId(), table.getNumber(), table.isOccupied());
                                return dto;
                            }
                    ).collect(Collectors.toList()));
            return new ResponseEntity<>(
                    restaurantDTO, HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    Collections.singletonMap("error", "Could not find restaurant with id "+ id), HttpStatus.NOT_FOUND
            );
        }
    }
}
