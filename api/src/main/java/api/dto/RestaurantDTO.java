package api.dto;

import java.util.List;

public class RestaurantDTO {

    private long id;

    private String name;

    private List<TableDTO> tables;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TableDTO> getTables() {
        return tables;
    }

    public void setTables(List<TableDTO> tables) {
        this.tables = tables;
    }

    public RestaurantDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
