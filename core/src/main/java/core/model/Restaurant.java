package core.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant extends GenericEntity{

    private String name;

    @OneToMany
    private List<Table> tables;

    public String getName() {
        return name;
    }

    public List<Table> getTables() {
        return tables;
    }
}
