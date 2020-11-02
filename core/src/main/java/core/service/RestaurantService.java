package core.service;

import core.model.Restaurant;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class RestaurantService extends GenericService<Restaurant>{
}
