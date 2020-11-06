package core.service;

import core.model.Order;
import core.model.Table;
import core.repository.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TableService extends GenericService<Table> {

    final private TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public void saveWithNewActiveOrder(Table table) {
        table.setOccupied(true);
        table.addEmptyActiveOrder();
        tableRepository.save(table);
    }

    public Order findActiveOrderWithItemsById(Long id) {
        return tableRepository.findActiveOrderWithItemsById(id);
    }
}
