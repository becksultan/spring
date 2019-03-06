package main;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddStudent {
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Min(value = 6)
    private Integer age;

    @NotNull
    private Integer class_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }
}
