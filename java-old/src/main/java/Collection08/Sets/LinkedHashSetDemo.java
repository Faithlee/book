package Collection08.Sets;

import java.util.*;
import java.util.stream.Stream;

public class LinkedHashSetDemo {

    public static void main(String[] args) {
        //todo LinkedHashSet：使用hashCode确定元素的储存位置，同时使用链表结构维护元素的顺序
        // HashSet的子类，性能差于HashSet，
        // 可以保证访问顺序与添加顺序一致

        linkedHashSetTest();
    }

    public static void linkedHashSetTest()
    {
        LinkedHashSet set = new LinkedHashSet();
        // 添加
        set.add("动物园");
        set.add("熊猫");
        set.add("大象");

        System.out.println(set);

        // 删除
        set.remove("熊猫");
        // 再添加
        set.add("北极熊");
        System.out.println(set);

        // foreach遍历
        for (Object obj: set) {
            String str = (String)obj;
            System.out.println(str);
        }

        // lambda遍历
        set.forEach(obj -> System.out.println("按添加顺序:" + obj));

        // iterator遍历
        Iterator it = set.iterator();
        it.forEachRemaining(obj -> System.out.println("iterator遍历: " + obj));

        // stream遍历
        Stream stream = set.stream();
        stream.forEach(obj -> System.out.println("Stream操作集合遍:" + obj));
    }
}

