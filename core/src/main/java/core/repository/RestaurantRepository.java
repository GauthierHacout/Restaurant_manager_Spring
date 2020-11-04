package core.repository;

import core.model.Restaurant;

public interface RestaurantRepository extends GenericRepository<Restaurant> {

    Restaurant findByName(String name);
}
