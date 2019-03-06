package main;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddSchool {
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
