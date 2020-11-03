package core.service;

import core.model.Restaurant;
import core.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class RestaurantService extends GenericService<Restaurant>{

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant findByName(String name) {
        return restaurantRepository.findByName(name);
    }
}
