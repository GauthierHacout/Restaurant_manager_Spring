package core.service;

import core.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService extends GenericService<OrderRepository> {

}


