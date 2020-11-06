package core.service;

import core.model.Table;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TableService extends GenericService<Table> {
}
