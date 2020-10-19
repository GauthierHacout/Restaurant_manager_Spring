package core.service;

import core.repository.RestaurantRepository;
import javax.transaction.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class RestaurantService extends Service<RestaurantRepository>{
}
