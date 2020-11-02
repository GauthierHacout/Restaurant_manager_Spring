package core.repository;

import core.model.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<T extends GenericEntity> extends JpaRepository<T, Long> {
}
