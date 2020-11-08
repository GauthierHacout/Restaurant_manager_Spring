package api.controller;

import api.dto.OrderDTO;
import api.dto.RestaurantDTO;
import api.dto.TableDTO;
import core.model.OrderItem;
import core.model.Restaurant;
import core.service.RestaurantService;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Path("/restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantController implements RestController{

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GET
    @Path("")
    public List<RestaurantDTO> findAllRestaurants(){
        return restaurantService.findAll().stream().map(
                restaurant -> {
                    RestaurantDTO restaurantDTO = new RestaurantDTO(restaurant.getId(), restaurant.getName());
                    return restaurantDTO;
                }
        ).collect(Collectors.toList());
    }

    @GET
    @Path("/{restaurantId}/tables")
    public RestaurantDTO findRestaurantTables(@PathParam("restaurantId") long id){
        Restaurant restaurant = restaurantService.findByIdWithTables(id);

        RestaurantDTO restaurantDTO = new RestaurantDTO(restaurant.getId(), restaurant.getName());
        restaurantDTO.setTables(
                restaurant.getTables().stream().map(
                        table -> {
                            TableDTO dto = new TableDTO(table.getId(), table.getNumber(), table.isOccupied());
                            return dto;
                        }
                ).collect(Collectors.toList()));
        return restaurantDTO;
    }
}
