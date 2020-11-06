package core.service;

import core.model.Order;
import core.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService extends GenericService<Order> {
}