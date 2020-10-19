package core.service;

import core.repository.ProductRepository;
import javax.transaction.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class ProductService extends Service<ProductRepository> {

}
