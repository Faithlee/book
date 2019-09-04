package annotation.base;

import annotation.annotations.IdAnnotation;
import annotation.annotations.PersistentAnnotation;
import annotation.annotations.PropertyAnnotation;

@PersistentAnnotation(table = "dog")
public class Pet {

    @IdAnnotation(column = "pet_id", type = "integer", generator = "identity")
    private Integer id;

    @PropertyAnnotation(column = "pet_name", type = "string")
    private String name;

    @PropertyAnnotation(column = "pet_age", type = "integer")
    private Integer age;

    public Pet() {

    }

    public Pet(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}

