package core.service;

import core.model.GenericEntity;
import core.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<S extends GenericEntity> {

    @Autowired
    private GenericRepository<S> repository;

    public List<S> findAll() {
        return repository.findAll();
    }

    public S save(S s) {
        return repository.save(s);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Optional<S> find(Long id) {
        return repository.findById(id);
    }

}
