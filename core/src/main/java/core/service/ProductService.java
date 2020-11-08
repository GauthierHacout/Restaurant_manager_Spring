package core.service;

import core.model.Product;
import core.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService extends GenericService<Product> {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllByRestaurantId(Long id) {
        return productRepository.findAllByRestaurantId(id);
    }
}
