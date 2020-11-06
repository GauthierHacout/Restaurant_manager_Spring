package core.repository;

import core.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends GenericRepository<Restaurant> {

    Restaurant findByName(String name);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.tables WHERE r.id=:id")
    Restaurant findByIdWithTables(@Param("id") Long id);
}
