package core.repository;

import core.model.Product;

import java.util.List;

public interface ProductRepository extends GenericRepository<Product> {

    List<Product> findAllByRestaurantId(Long id);
}
