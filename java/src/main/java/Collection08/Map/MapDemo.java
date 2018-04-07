package Collection08.Map;

import java.util.*;

/**
 * Map集合
 */
public class MapDemo {

    public static void main(String[] args)
    {
        // map
        hashMapTest();

        // map
        hashMapTest2();

        // null test
        hashMapNullKeyAndValueTest();

        // 判断key的标准
        hashMapEqualsAndHashCodeTest();

        // 修改实例对象的可变成员，会导致无法正确访问到该元素
        // 结论：尽量不使用可变对象作为HashMap的key，如果需要的话也尽量不要修改key
        mutableObjectTest();
    }


    public static void hashMapTest()
    {
        // todo Map保存具有映射关系的一对一数据，集合里保存key和value两组数据
        // todo key和value可以是任何引用，但key不允许重复
        // todo 同一个Map对象的任何两个key通过equals对比都是false
        // todo Set和Map关系非常紧密，可以把Set看作是Map的value为null的集合

        Map map = new HashMap();

        map.put("疯狂Java讲义", 109);
        map.put("疯狂iOS讲义", 10);
        map.put("疯狂Ajax讲义", 89);
        map.put("轻量级JavaEE实战", 89);

        System.out.println("查看Map集合: " + map);

        System.out.println("覆盖原来的值，会返回原来的值: " + map.put("疯狂iOS讲义", 99));

        // containsKey()
        // 通过对比key对象的equals()方法
        System.out.println("是否包含疯狂iOS讲义：" +  map.containsKey("疯狂iOS讲义"));

        // containsValue()
        System.out.println("是否包含99的value：" + map.containsValue(99));

        // 遍历hashMap，返回映射关系
        for (Object obj: map.keySet()) {
            String key = (String)obj;

            System.out.println("key => " + map.get(key));
        }

        // remove
        map.remove("疯狂Ajax讲义");
        System.out.println("删除后集合元素：" + map);
    }

    /**
     * Map新增方法测试
     */
    public static void hashMapTest2()
    {
        System.out.println("\n------------------我是分割线--------------------");

        Map map = new HashMap();

        map.put("疯狂Java讲义", 109);
        map.put("疯狂iOS讲义", 10);
        map.put("疯狂Ajax讲义", 89);
        map.put("轻量级JavaEE实战", 89);

        // replace()，只做替换，不存在不会新增
        map.replace("疯狂XML讲义", 99);

        // merge()：将key对应的value与新value作为参数，运算后改变原value
        map.merge("疯狂iOS讲义", 88, (oldVal, param) -> (Integer)oldVal + (Integer)param);
        System.out.println("merge操作: " + map);

        // Java不存在，对应的value为null，将计算结果作为新的value
        map.computeIfAbsent("Java", (key) -> ((String)key).length());
        System.out.println("computeIfAbsent操作: " + map);
        //todo 对比
        map.computeIfAbsent("疯狂Java讲义", (key) -> ((String)key).length());
        System.out.println("对比computeIfAbsent操作: " + map);

        // computeIfPresent: 当key对应的value存在时，使用新计算的value替换
        map.computeIfPresent("Java", (key, value)->(Integer)value * (Integer)value);
        System.out.println("computeIfPresent操作: " + map);
        // todo 对比
        map.computeIfPresent("Ajax", (key, value)->(Integer)value * (Integer)value);
        System.out.println("computeIfPresent操作: " + map);
    }

    /**
     * key为null的映射关系
     */
    public static void hashMapNullKeyAndValueTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo HashMap 可以使用null的key和value，但Hashtable不可以添加null的映射关系
        HashMap map = new HashMap();

        // 重复添加
        map.put(null, null);
        map.put(null, null);

        map.put("a", null);

        System.out.println("打印null的Map：" + map);
    }

    /**
     * 判断key的标准为equals与hashCode()
     */
    public static void hashMapEqualsAndHashCodeTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        HashMap map = new HashMap();
        //Hashtable map = new Hashtable();
        map.put(new A(60000), "疯狂Java讲义");
        map.put(new A(87563), "轻量级Java EE企业应用实战");
        map.put(new A(1232), new B());

        System.out.println("查看集合元素: " + map);

        // todo value判断是否相同：只要两个对象通过equals对比返回true，则认为它们就是相同value
        // todo HashMap需要检验value的hashCode？
        System.out.println("是否包含指定的value: " + map.containsValue("测试字符串"));
        //System.out.println("是否包含指定的value: " + map.containsValue(new A(1232)));
        System.out.println("是否包含指定的value: " + map.containsValue(new B()));

        // todo key判断标准：只要两个对象通过equal对比返回true，对比hashCode并且相同，则认为是相同的
        System.out.println("是否包含指定的key: " + map.containsKey(new A(87563)));

        // remove()
        map.remove(new A(1232));
        System.out.println("remove操作后：" + map);
    }

    /**
     * 可变对象的hashCode会影响map集合的操作
     */
    public static void mutableObjectTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo 修改可变对象的key，程序无法访问Map中被修改的key，但未修改的key还可以操作
        HashMap map = new HashMap();

        map.put(new A(60000), "疯狂Java讲义");
        map.put(new A(87563), "轻量级Java EE企业应用实战");
        map.put(new A(10000), "疯狂Ajax讲义");

        System.out.println("打印map集合: " + map);

        // key的集合
        Iterator it = map.keySet().iterator();
        A first = (A)it.next();

        // todo 修改可变对象与已存在对象的hashCode一致，引起冲突
        first.count = 87563;
        System.out.println("打印修改后的集合: " + map);

        // todo 测试remove操作: 只能删除没有被修改过的key-value, 87563认为没有修改
        map.remove(new A(87563));
        System.out.println("打印删除后的集合: " + map);

        // 无法查找
        System.out.println(map.get(new A(87563)));
        System.out.println(map.get(new A(60000)));

        // 查找正常
        System.out.println(map.get(new A(10000)));
    }
}

class A {
    int count;

    public A(int count)
    {
        this.count = count;
    }

    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        }

        if (obj != null && obj.getClass() == A.class) {
            A a = (A)obj;
            return this.count == a.count;
        }

        return  false;
    }

    public int hashCode()
    {
        return this.count;
    }
}

class B {
    public boolean equals(Object obj)
    {
        return true;
    }

    public int hashCode()
    {
        return 1232;
    }
}
