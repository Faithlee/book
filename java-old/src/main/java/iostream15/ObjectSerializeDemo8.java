package iostream15;

import java.io.*;

/**
 * 对象序列化操作
 */
public class ObjectSerializeDemo8 {

    public static void main(String[] args)
    {
        // 序列化的对象可实现磁盘储存或网络传输，
        // todo 实现序列化的条件：对象必须实现Serialize接口或Extenalizable接口
        // todo 反序列化：将二进制流恢复为对象，恢复对象时必须提供所属的class文件，否则会引用异常
        // 反序列化无须通过构造器初始化对象

        // todo * java序列化机制：
        // 1. 所有保存到磁盘的对象有一个序列编号
        // 2. 只有对象从未被序列化，系统才会将对象转换为字节码并输出
        // 3. 序列化过的对象，程序直接输出序列化编号，而不在重新序列化

        // todo 操作过程:
        // 序列化：ObjectOutputStream.writeObject()
        // 反序列化：ObjectInputStream.readObject()

        ObjectSerializeDemo8 object = new ObjectSerializeDemo8();
        // todo 1. 基本类型的序列化操作
        // 序列化
        object.serializeTest();
        // 反序列化
        object.unserializeTest();

        // todo 2. 对象引用的序列化
        object.referenceSerializeTest();
        object.referenceUnserializeTest();

        // todo 3. 可变对象的序列化
        object.mutableObjectSerializeTest();
        object.mutableObjectUnserializeTest();
    }

    /**
     * 序列化person对象
     */
    public void serializeTest()
    {
        System.out.println("-----------我是分割线----------");
        try (
            FileOutputStream file = new FileOutputStream("object.txt");
            // 1. 创建ObjectOutputStream输出流
            ObjectOutputStream stream = new ObjectOutputStream(file);
        ) {
            // 将对象写入文件
            Person person = new Person("孙悟空", 500);
            stream.writeObject(person);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 反序列化对象
     */
    public void unserializeTest()
    {
        System.out.println("-----------我是分割线----------");

        try (
            FileInputStream stream = new FileInputStream("object.txt");
            ObjectInputStream object = new ObjectInputStream(stream)
        ) {
            // todo 反序列化时并不会执行构造器
            Person person = (Person)object.readObject();
            // 输出反序化后对象的属性
            System.out.println(person.getName() + ": " + person.getAge());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *  引用序列化操作
     */
    public void referenceSerializeTest()
    {
        System.out.println("-----------我是分割线----------");

        // todo 成员变量是引用类型，引用类型也必须可序列化，否则Teacher不可序列化
        // 为了反序列化时可以正常恢复Teacher对象，程序会将Person类也序列化

        try (
            FileOutputStream stream = new FileOutputStream("reference_object.txt");
            ObjectOutputStream object = new ObjectOutputStream(stream);
        ) {
            Person person = new Person("孙悟空", 500);
            Teacher t1 = new Teacher("唐僧", person);
            Teacher t2 = new Teacher("菩提祖师", person);

            // todo 序列化
            // 实际只序列化了3个，可以通过检查反序列化的对象判断是否相同
            object.writeObject(t1);
            object.writeObject(t2);
            object.writeObject(person);
            object.writeObject(t2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 引用对象反序列化
     */
    public void referenceUnserializeTest()
    {
        System.out.println("-----------我是分割线----------");

        try (
            FileInputStream stream = new FileInputStream("reference_object.txt");
            ObjectInputStream object = new ObjectInputStream(stream);
        ) {
            Teacher t1 = (Teacher)object.readObject();
            Teacher t2 = (Teacher)object.readObject();
            Person  p  = (Person)object.readObject();
            Teacher t3 = (Teacher)object.readObject();
            // True
            System.out.println("t1的student引用和p是否相同：" + (t1.getStudent() == p));

            // True
            System.out.println("t2的student引用和p是否相同：" + (t2.getStudent() == p));

            // True
            System.out.println("t2和t3是否为同一个对象：" + (t2 == t3));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 可变对象序列化
     */
    public void mutableObjectSerializeTest()
    {
        System.out.println("-----------我是分割线----------");

        // todo 序列化可对象时，只有第一次使用writeObject会序列化，再次调用只会输出前面序列化编号，
        // 即使修改实例的变量，改变的实例变量的值也不会输出

        try (
            FileOutputStream stream = new FileOutputStream("mutable_object.txt");
            ObjectOutputStream object = new ObjectOutputStream(stream);
        ) {
            Person person = new Person("孙悟空", 500);
            // 序列化并输出
            object.writeObject(person);

            // 改变person的name实例变量，只输出序列化编号
            person.setName("猪悟能");
            object.writeObject(person);
            // todo 对比反序列化的结果查看下方法
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 可变对象反序列化
     */
    public void mutableObjectUnserializeTest()
    {
        System.out.println("-----------我是分割线----------");

        try (
            FileInputStream stream = new FileInputStream("mutable_object.txt");
            ObjectInputStream object = new ObjectInputStream(stream);
        ) {
            // todo 对比反序列化的结果
            Person p1 = (Person)object.readObject();
            Person p2 = (Person)object.readObject();

            // True
            System.out.println("可变对象反序列化：" + (p1 == p2));
            // 改变实例变量后并没有被序列化: 孙悟空
            System.out.println("p1.name: " + p1.getName());
            System.out.println("p2.name: " + p2.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


class Person implements Serializable
{
    private String name;
    private int age;

    public Person()
    {
        System.out.println("test test");
    }

    // 并没有提供无参构造器
    public Person(String name, int age)
    {
        System.out.println("有参数的构造器……");
        this.name = name;
        this.age  = age;
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

class Teacher implements Serializable
{
    private String name;
    private Person student;

    public Teacher(String name, Person student)
    {
        this.name = name;
        this.student = student;
    }

    // getter/setter省略


    public Person getStudent() {
        return this.student;
    }
}
