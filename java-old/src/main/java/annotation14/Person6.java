package annotation14;

import annotation14.annotation.Id;
import annotation14.annotation.Property;

public class Person6 {

    @Id(column = "person_id", type = "integer", generator = "identity")
    private int id;

    @Property(column = "person_name", type = "string")
    private String name;

    @Property(column = "person_age", type = "integer")
    private int age;

    public Person6()
    {

    }

    /**
     * 构造器
     * @param id
     * @param name
     * @param age
     */
    public Person6(int id, String name, int age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return this.age;
    }
}

