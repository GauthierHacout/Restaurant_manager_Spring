package core.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
