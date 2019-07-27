package Collection08.List;

import java.util.*;

public class ArrayListDemo {

    public static void main(String[] args)
    {
        // todo ArrayList底层封装了一个允许动态分配的object[]
        // 需要添加大量元素时，一次性设置ensureCapacity到位，提升性能；

        // List 接口
        listTest();

        // equals()测试
        checkObjectEquals();

        // 排序
        sortAndReplaceTest();

        // ListIterator
        listIteratorTest();

        // 固定长度List
        fixedListTest();
    }


    public static void listTest()
    {
        // todo List接口实现Collection接口，集合有序的并且元素可以重复的，还可以通过索引操作元素
        // todo List判断两个对象相等的标准：equals()

        List books = new ArrayList();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂的android讲义"));

        System.out.println(books);

        // todo add()：添加到指定索引位置，后面的元素会向后移一位
        books.add(1, new String("疯狂Ajax讲义"));
        for (int i = 0; i < books.size(); i++) {
            System.out.println("遍历List集合: " + books.get(i));
        }

        // 删除元素
        System.out.println("通过索引移除元素：" + books.remove(2));

        // 查询指定元素的位置
        System.out.println("查找元素的索引位置：" + books.indexOf(new String("疯狂Ajax讲义")));

        // 替换元素
        System.out.println("替换元素：" + books.set(1, new String("疯狂Java讲义")));
        System.out.println("查看集合：" + books);

        // 截取子集合
        System.out.println("截取子集合: " + books.subList(1,2));
    }

    /**
     * 判断元素是否相同
      */
    public static void checkObjectEquals()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo 判断依据：两个对象是否相等使用equals()决定，比如remove、indexOf时
        List books = new ArrayList();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂的android讲义"));

        System.out.println("集合中元素：" + books);

        // todo 检查索引是否存在
        System.out.println("检查元素的索引：" + books.indexOf(new A()));

        // todo 会删除集合中第一个元素
        System.out.println("会删除第一个元素：" + books.remove(new A()));
    }

    /**
     * 列表排序与替换
     */
    public static void sortAndReplaceTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo sort()：通过Comparator对象控制排序（lambda）
        // todo replaceAll()：函数式接口

        List books = new ArrayList();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂的android讲义"));
        books.add(new String("疯狂iOS讲义"));

        // sort
        books.sort((o1, o2) -> ((String)o1).length() - ((String)o2).length());
        System.out.println("sorted：" + books);

        // replaceAll()
        books.replaceAll(ele -> ((String)ele).length());
        System.out.println("replaceAll" + books);
    }

    /**
     * 向前遍历迭代器
     */
    public static void listIteratorTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        String[] books = {
                "疯狂Java讲义",
                "疯狂iOS讲义",
                "轻量级Java EE企业级应用实战"
        };

        List bookList = new ArrayList();

        for (int i = 0; i < books.length; i++) {
            bookList.add(books[i]);
        }

        ListIterator it = bookList.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            it.add("-----分隔线------");
        }

        System.out.println("返向迭代:");
        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }

    /**
     * 固定长度的List
      */
    public static void fixedListTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo 此List不是ArrayList或Vector的实现类，而是Arrays的内部类
        // todo 此List的长度是固定的，长度无法增删

        List fixedList = Arrays.asList("疯狂Java讲义", "疯狂Ajax讲义");

        System.out.println("内部类：" + fixedList.getClass());

        fixedList.forEach(System.out::println);

        // todo 不能增删
//         fixedList.add("aabb");
    }
}

/**
 * 判断两个对象是否相同，通过equals()确定
 */
class A {
    public boolean equals(Object obj)
    {
        return true;
    }
}