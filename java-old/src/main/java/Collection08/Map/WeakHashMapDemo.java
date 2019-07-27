package Collection08.Map;

import java.util.*;

/**
 * WeakHashMap
 */
public class WeakHashMapDemo {

    public static void main(String[] args)
    {
        // todo HashMap的key保留了对象的强引用
        // todo HashMap对象本身不销毁，则引用的对象的key-value不会被垃圾回收
        // todo WeakHashMap的key保留了对象的弱引用（key所引用的对象没有被强引用变量所引用），垃圾回收的时候会把WeakHashMap中的元素删除

        weakHashMapTest();
    }


    public static void weakHashMapTest()
    {
        // todo WeakHashMap的key保留了对象的弱引用，因此不应该让key所引用的对象具有强引用

        WeakHashMap map = new WeakHashMap();

        map.put(new String("语文"), new String("良好"));
        map.put(new String("数学"), new String("及格"));
        map.put(new String("英文"), new String("优秀"));

        // todo 下面的操作方式不推荐
        // 强引用1
        map.put("java", new String("中等"));

        // 强引用2
        String php = new String("php");
        map.put(php, new String("优秀"));

        System.out.println(map);

        // 通知系统垃圾回收
        System.gc();
        System.runFinalization();

        System.out.println("查看弱引用垃圾回收：" + map);
    }
}
