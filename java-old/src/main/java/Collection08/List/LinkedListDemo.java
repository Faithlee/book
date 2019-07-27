package Collection08.List;

import java.util.*;

/**
 * 多功能列表：可实现List集合接口，栈、队列功能
 */
public class LinkedListDemo {

    public static void main(String[] args)
    {
        linkedListTest();
    }

    /**
     * LinkedList
     */
    public static void linkedListTest()
    {
        // todo 底层实现原理与ArrayList、ArrayDeque不同，通过链表结构实现
        // 插入删除性能出色，推荐使用迭代器遍历
        // 随机访问性能差

        LinkedList books = new LinkedList();

        // 队列操作
        books.offer("疯狂Java讲义");
        // 栈操作
        books.push("轻量级Java企业应用实战");
        // 队列操作t
        books.offerFirst("疯狂iOS讲义");

        for (int i = 0; i < books.size(); i++) {
            System.out.println("遍历：" + books.get(i));
        }

        // 访问队列第一个元素
        System.out.println("队列第一个元素：" + books.peekFirst());

        // 访问栈第一个元素
        System.out.println("队列第一个元素：" + books.peek());

        // 栈操作：出栈
        System.out.println("出栈：" + books.pop());

        // 查看元素
        System.out.println("元素：" + books);

        // 队列操作：删除最后一个元素
        System.out.println("最后一个元素出队：" + books.pollLast());

        // 查看元素
        System.out.println("元素：" + books);
    }
}
