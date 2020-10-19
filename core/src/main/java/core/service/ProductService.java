package core.service;

import core.repository.ProductRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService extends GenericService<ProductRepository> {

}
