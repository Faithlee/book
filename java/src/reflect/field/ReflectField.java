package reflect.field;

import java.lang.reflect.Field;

/**
 * 反射成员变量
 */
public class ReflectField {

    public static void main(String[] args) throws Exception {
        Person person = new Person();

        Class<Person> clazz = Person.class;
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person, "Yee.Lee");

        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(person, 30);

        System.out.println(person);
    }
}

class Person {

    private String name;

    private int age;

    public String toString() {
        return "Person[name:" + name + ", age:" + age + " ]";
    }
}
