package Collection08.Map;

import java.util.IdentityHashMap;

/**
 * 与HashMap类似，对于key的判断要更严格
 */
public class IdentityHashMapDemo {

    public static void main(String[] args)
    {
        // todo 对于key的判断标准：当且仅当两个key严格相等（key1==key2）
        // todo 相对于HashMap，对比两个key对象的equals()相等则认为是同一对象

        keyIdentityTest();
    }

    /**
     * 测试key恒等
     */
    public static void keyIdentityTest()
    {
        IdentityHashMap map = new IdentityHashMap();

        // 通过 == 比较不相等
        map.put(new String("语文"), 89);
        map.put(new String("语文"), 99);

        System.out.println("比较结果：" + (new String("语文") == new String("语文")));

        // todo key是字符串直接量，字符序列完全相同(Java使用常量池管理字符串直接量)
        // IdentityHashMap认为是同一个key
        map.put("java", 88);
        map.put("java", 98);

        System.out.println(map);
    }

}
