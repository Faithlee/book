package Collection08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class CollectionTest {

    public static void main(String[] args) {
        // todo
        testList();

        // HashSet测试
        testHashSet();

        compare();
    }

    /**
     * List集合测试
     */
    public static void testList()
    {
        Collection c = new ArrayList();

        // todo 添加元素
        c.add("孙悟空");
        // 自动装箱添加元素
        c.add(6);
        // 查看集合中的元素
        System.out.println("c集合元素的打印: " + c);
        // todo 统计元素个数
        System.out.println("c集合元素的个数: " + c.size());

        // todo 删除指定元素
        c.remove(6);
        System.out.println("c集合元素的个数: " + c.size());

        // todo 检查是否包含元素: contains
        System.out.println("c中是否包含孙悟空元素: " + c.contains("孙悟空"));

        // 继续添加元素
        c.add("Java企业应用!");

        System.out.println("c集合元素的打印: " + c);
    }

    /**
     *  Set集合测试
     */
    public static void testHashSet()
    {
        Collection books = new HashSet();

        books.add("java ee 企业应用实战");
        books.add("疯狂java讲义");

        System.out.println(books);
    }

    public static void compare()
    {
        System.out.println("\ncompare----\n");
        Collection c = new ArrayList();

        c.add("孙悟空");
        c.add(6);
        // 删除
        c.remove(6);
        c.add("Java企业应用!");

        System.out.println("c集合元素的打印: " + c);


        // Set集合
        Collection books = new HashSet();

        books.add("java ee 企业应用实战");
        books.add("疯狂java讲义");

        // 检查集合c里是否包含books集合
        System.out.println("C集合是否包含books集合:" + c.contains(books));

        // 移除books集合
        c.removeAll(books);
        System.out.println("c集合里的元素"  + c);

        // 清除集合里所有元素
        c.clear();
        System.out.println("c集合里的元素:" + c);

        // books与c取交集
        books.retainAll(c);
        System.out.println("books集合里的元素：" + books);
    }
}
