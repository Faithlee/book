package Collection08;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * 8.2 集合遍历操作
 */
public class Traverse {

    public static void main(String[] args) {

        // lambda
        lambdaTest();

        // iterator
        iteratorTest();

        // 迭代时修改元素本身
        iteratorTest2();

        // iterator.forEachRemaining()
        iteratorTest3();

        // foreachTest
        foreachTest();

        // Predicate.removeIf()接口
        filterTest();

        // Stream操作集合
        streamTest();

        // 集合的stream()流式操作
        collectionStreamTest();
    }

    /**
     * lambda表达式遍历集合
     */
    public static void lambdaTest()
    {
        Collection books = new HashSet();

        books.add("轻量级JavaEE企业级实战");
        books.add("疯狂Java讲义");
        books.add("疯狂android讲义");

        // forEach是Collection父接口Iterable的函数式接口
        // forEach(Consumer action): 将元素依次传递给accept(T t)方法
        books.forEach(obj -> System.out.println("迭代集合元素: " + obj));
    }

    /**
     *  迭代器遍历
     */
    public static void iteratorTest()
    {
        Collection books = new HashSet();

        books.add("轻量级JavaEE企业级实战");
        books.add("疯狂Java讲义");
        books.add("疯狂android讲义");

        Iterator it = books.iterator();
        while (it.hasNext()) {
            String book = (String)it.next();

            System.out.println(book);

            // 删除集合中的删除
            if (book.equals("疯狂Java讲义")) {
                it.remove();
            }

            // todo 不会修改元素本身，迭代时并没有把元素本身传递给变量，只是把元素的值传给了迭代变量
            book = "测试字符串";

            // todo 一定不要改集合中的元素：ConcurrentModificationException，会引发线程安全问题
            //books.add("非法操作");
            //books.remove("疯狂Java讲义");
        }

        System.out.println("books: " + books);
    }


    /**
     * todo 迭代时可以修改元素本身，但不能修改集合的元素的增减
     */
    public static void iteratorTest2()
    {
        Collection animal = new HashSet();

        Animal littleCat = new Animal(9);
        animal.add(littleCat);

        Iterator it = animal.iterator();
        while (it.hasNext()) {
            Animal cat = (Animal)it.next();

            // test修改元素本身没有影响，长一岁
            cat.age++;
        }

        // 长大一岁
        System.out.println("cat.age: " + littleCat.age);
    }


    /**
     *  Iterator.forEachRemaining()接口
     */
    public static void iteratorTest3()
    {
        Collection books = new HashSet();

        books.add("轻量级JavaEE企业级实战");
        books.add("疯狂Java讲义");
        books.add("疯狂android讲义");

        Iterator it = books.iterator();
        it.forEachRemaining(obj -> System.out.println("迭代集合元素: " + obj));
    }

    /**
     * foreach循环遍历
     */
    public static void foreachTest()
    {
        Collection books = new HashSet();

        books.add("轻量级JavaEE企业级实战");
        books.add("疯狂Java讲义");
        books.add("疯狂android讲义");

        for (Object obj: books) {
            String book = (String)obj;

            System.out.println(book);

            // 与使用迭代器类似
            // todo 迭代时不能修改集合元素，尽管有时不会触发异常
            if (book.equals("疯狂Java讲义")) {
                books.remove(book);
            }
            // todo 迭代时不能修改集合元素
            if (book.equals("疯狂android讲义")) {
                //books.remove(book);
            }

            // todo 遍历时操作的也不是元素本身
            book = "我不会被修改";
        }

        System.out.println("books元素：" + books);
    }


    /**
     *  Predicate操作集合用于删除符合条件的
     */
    public static void filterTest()
    {
        Collection books = new HashSet();

        books.add("轻量级JavaEE企业级实战");
        books.add("疯狂Java讲义");
        books.add("疯狂android讲义");

        // todo removeIf
        books.removeIf(obj -> ((String)obj).length() < 10);
        System.out.println("books删除长度小于10的字符串：" + books);

        // 统计包含疯狂
        System.out.println("包含疯狂: " + calAll(books, ele -> ((String)ele).contains("疯狂")));

        System.out.println("包含Java: " + calAll(books, ele -> ((String)ele).contains("Java")));

        System.out.println("长度大于10：" + calAll(books, ele -> ((String)ele).length() > 10));
    }

    protected static int calAll(Collection books, Predicate p)
    {
        int total = 0;

        for (Object obj: books) {
            String book = (String)obj;
            if (p.test(obj)) {
                total++;
            }
        }

        return  total;
    }

    /**
     * stream操作集合：链式调用
     */
    public static void streamTest()
    {
        IntStream is = IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .build();

        // todo 以下方法调用都是终端方法，只能调用一次
//        System.out.println("所有元素最大值: " + is.max().getAsInt());
//        System.out.println("所有元素最小值: " + is.min().getAsInt());
//        System.out.println("所有元素总和: " + is.sum());
//        System.out.println("平均值: " + is.average());
//        System.out.println("所有元素平方大于20: " + is.allMatch(ele -> (ele * ele) > 20));
//        System.out.println("元素平方存在大于20的: " + is.anyMatch(ele -> ele * ele > 20));

        IntStream newIs = is.map(ele -> ele * 2 + 1);
        newIs.forEach(ele -> System.out.println("map： " + ele));
    }


    /**
     *  Collection.stream()流操作方法
     */
    public static void collectionStreamTest()
    {
        Collection books = new HashSet();

        books.add("轻量级JavaEE企业级实战");
        books.add("疯狂Java讲义");
        books.add("疯狂android讲义");

        System.out.println("包含疯狂数量: " + books.stream().filter(ele -> ((String)ele).contains("疯狂")).count());

        System.out.println("字符长度大于10: " + books.stream().filter(ele -> ((String)ele).length() > 10).count());

        books.stream().mapToInt(ele -> ((String)ele).length()).forEach(obj -> System.out.println("字符长度: " + obj));
    }
}

class Animal
{
    public int age = 0;

    public Animal(int age)
    {
        this.age = age;
    }
}
