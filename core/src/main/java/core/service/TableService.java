package core.service;

import core.repository.TableRepository;
import javax.transaction.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class TableService extends Service<TableRepository> {
}
