package core.service;

import core.model.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService extends GenericService<Order> {
}


