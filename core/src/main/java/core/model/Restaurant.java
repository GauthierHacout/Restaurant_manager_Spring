package core.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant extends GenericEntity{

    private String name;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<Table> tables;

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return tables;
    }
}
