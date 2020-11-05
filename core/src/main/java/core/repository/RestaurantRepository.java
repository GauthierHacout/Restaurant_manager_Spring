package core.repository;

import core.model.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends GenericRepository<Restaurant> {

    Restaurant findByName(String name);
}
