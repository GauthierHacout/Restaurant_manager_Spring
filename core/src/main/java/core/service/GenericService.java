package core.service;

import core.model.GenericEntity;
import core.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class GenericService<S extends GenericEntity> {

    @Autowired
    private GenericRepository<S> dao;

    public List<S> findAll() {
        return dao.findAll();
    }

    public S create(S s) {
        return dao.save(s);
    }

    public void deleteAll() {
        dao.deleteAll();
    }

}
