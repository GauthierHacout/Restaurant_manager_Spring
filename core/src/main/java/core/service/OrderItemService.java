package core.service;

import core.model.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderItemService extends GenericService<OrderItem>{
}
