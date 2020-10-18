package core.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany
    private List<Table> tables;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Table> getTables() {
        return tables;
    }
}
