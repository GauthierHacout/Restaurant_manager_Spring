package core.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericService<T extends JpaRepository> {

    private T t;

    public List findAll() {
        return t.findAll();
    }

}
