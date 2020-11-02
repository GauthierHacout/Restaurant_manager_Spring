package core.service;

import core.model.Product;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService extends GenericService<Product> {

}
