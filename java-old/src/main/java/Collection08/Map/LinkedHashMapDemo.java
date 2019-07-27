package Collection08.Map;

import java.util.LinkedHashMap;

/**
 * LinkedHashMap 实现类
 */
public class LinkedHashMapDemo {

    public static void main(String[] args)
    {
        // 内部通过双向链表实现，维护key-value的顺序，与插入顺序一致
        linkedHashMapTest();
    }

    /**
     * 测试LinkedHashMap
     */
    public static void linkedHashMapTest()
    {
        // todo 需要维护插入顺序，性能低于HashMap
        // todo 迭代访问时元素时有较好的性能
        LinkedHashMap score = new LinkedHashMap();

        score.put("语文", 80);
        score.put("英文", 99);
        score.put("数学", 90);

        score.forEach((key, value) -> System.out.println(key + " ---> " + value));
    }
}
