package core.service;

import core.repository.OrderRepository;
import javax.transaction.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class OrderService extends Service<OrderRepository> {

}


