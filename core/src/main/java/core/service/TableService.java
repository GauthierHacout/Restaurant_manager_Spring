package core.service;

import core.repository.TableRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class TableService extends GenericService<TableRepository> {
}
