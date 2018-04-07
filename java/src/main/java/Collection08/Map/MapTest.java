package Collection08.Map;

import java.util.*;

/**
 * java集合使用泛型
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value 1");
        map.put("2", "value 2");
        map.put("3", "value 3");

        // 1. 普通使用
        System.out.println("通过Map.keySet遍历key和value");
        for (String key : map.keySet()) {
            System.out.println("key=" + key + " and value=" + map.get(key));
        }

        // todo
        System.out.println("\n\n2. 通过map.entrySet使用Iterator遍历key和value");
        Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,String> entry = it.next();
            System.out.println("key=" + entry.getKey() + "; value=" + entry.getValue());
        }

        // todo 大容量推荐使用
        System.out.println("\n\n3. 通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key=" + entry.getKey() + " and value=" + entry.getValue());
        }

        // todo
        System.out.println("\n\n4. 通过map.values()遍历所有的value");
        for (String v : map.values()) {
            System.out.println("value=" + v);
        }
    }

    public static <T extends Comparable<T>> T maximum(T x, T y, T z)
    {
        return x;
    }

    public static <E> void printArr(E[] arr)
    {
        for (E element : arr) {
            System.out.printf("%s\n", element);
        }

    }


}
