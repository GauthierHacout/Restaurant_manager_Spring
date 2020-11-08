package core.service;

import core.model.Restaurant;
import core.model.Table;
import core.repository.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Transactional
public class TableService extends GenericService<Table> {

    final private TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public void saveWithNewActiveOrder(Table table) {
        if (!table.hasActiveOrder()) {
            table.setOccupied(true);
            table.addEmptyActiveOrder();
            tableRepository.save(table);
        } else {
            throw new Error("table already has an active order");
        }
    }

    public void setRestaurantAndSave(Restaurant restaurant, Table table) {
        table.setRestaurant(restaurant);
        tableRepository.save(table);
    }

    public Table findByIdWithOrders(Long id) {
        return tableRepository.findByIdWithOrders(id);
    }
}
