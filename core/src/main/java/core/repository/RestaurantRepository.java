package core.repository;

import core.model.Restaurant;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantRepository extends GenericRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r WHERE r.name = ?1")
    Restaurant findByName(String name);
}
