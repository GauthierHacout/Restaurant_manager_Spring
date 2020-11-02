package core.service;

import core.model.Table;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class TableService extends GenericService<Table> {
}
