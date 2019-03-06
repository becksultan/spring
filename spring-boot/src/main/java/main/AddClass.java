package main;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddClass {
    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 11)
    private Integer number;

    @NotNull
    private Character litera;

    @NotNull
    private Integer school_id;

    public Integer getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Integer school_id) {
        this.school_id = school_id;
    }

    public Character getLitera() {
        return litera;
    }

    public void setLitera(Character litera) {
        this.litera = litera;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
