package core.service;

import core.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class RestaurantService extends GenericService<RestaurantRepository>{
}
